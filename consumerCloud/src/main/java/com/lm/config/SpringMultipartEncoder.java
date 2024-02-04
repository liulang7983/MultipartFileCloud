package com.lm.config;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.ContentType;
import feign.form.FormEncoder;
import feign.form.MultipartFormContentProcessor;
import feign.form.spring.SpringManyMultipartFilesWriter;
import feign.form.spring.SpringSingleMultipartFileWriter;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author 李明
 * @date Created in 2023/9/1 0:07
 */
public class SpringMultipartEncoder extends FormEncoder {
    public SpringMultipartEncoder() {
        this(new Default());
    }

    public SpringMultipartEncoder(Encoder delegate) {
        super(delegate);
        MultipartFormContentProcessor processor = (MultipartFormContentProcessor)this.getContentProcessor(ContentType.MULTIPART);
        processor.addFirstWriter(new SpringSingleMultipartFileWriter());
        processor.addFirstWriter(new SpringManyMultipartFilesWriter());
    }
    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        if (bodyType.equals(MultipartFile[].class)) {
            MultipartFile[] files = (MultipartFile[])((MultipartFile[])object);
            Map<String, MultipartFile[]> data = Collections.singletonMap("files", files);
            super.encode(data, MAP_STRING_WILDCARD, template);
        } else if (bodyType.equals(MultipartFile.class)) {
            Map<String, Object> data = Collections.singletonMap("file", object);
            super.encode(data, MAP_STRING_WILDCARD, template);
        } else if (this.isMultipartFileCollection(object)) {
            Iterable<?> iterable = (Iterable)object;
            HashMap<String, Object> data = new HashMap();
            Iterator var6 = iterable.iterator();

            while(var6.hasNext()) {
                Object item = var6.next();
                MultipartFile file = (MultipartFile)item;
                data.put(file.getName(), file);
            }

            super.encode(data, MAP_STRING_WILDCARD, template);
        } else {
            super.encode(object, bodyType, template);
        }

    }

    private boolean isMultipartFileCollection(Object object) {
        if (!(object instanceof Iterable)) {
            return false;
        } else {
            Iterable<?> iterable = (Iterable)object;
            Iterator<?> iterator = iterable.iterator();
            return iterator.hasNext() && iterator.next() instanceof MultipartFile;
        }
    }
}
