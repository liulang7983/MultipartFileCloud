package com.lm.config;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.ContentType;
import feign.form.FormEncoder;
import feign.form.MultipartFormContentProcessor;

import feign.form.spring.SpringManyMultipartFilesWriter;
import feign.form.spring.SpringSingleMultipartFileWriter;
import lombok.val;
import org.springframework.web.multipart.MultipartFile;
/**
 * @author 李明
 * @date Created in 2023/8/31 23:46
 */
public class SpringFormEncoder extends FormEncoder {

    /**
     * Constructor with the default Feign's encoder as a delegate.
     */
    public SpringFormEncoder () {
        this(new Encoder.Default());
    }

    /**
     * Constructor with specified delegate encoder.
     *
     * @param delegate  delegate encoder, if this encoder couldn't encode object.
     */
    public SpringFormEncoder (Encoder delegate) {
        super(delegate);

        MultipartFormContentProcessor processor = (MultipartFormContentProcessor) getContentProcessor(ContentType.MULTIPART);
        processor.addFirstWriter(new SpringSingleMultipartFileWriter());
        processor.addFirstWriter(new SpringManyMultipartFilesWriter());
    }

    @Override
    public void encode (Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        if (bodyType.equals(MultipartFile[].class)) {
            val files = (MultipartFile[]) object;
            val data = new HashMap<String, Object>(files.length, 1.F);
            for (val file : files) {
                data.put(file.getName(), file);
            }
            super.encode(data, MAP_STRING_WILDCARD, template);
        } else if (bodyType.equals(MultipartFile.class)) {
            val file = (MultipartFile) object;
            val data = Collections.singletonMap(file.getName(), object);
            super.encode(data, MAP_STRING_WILDCARD, template);
        } else if (isMultipartFileCollection(object)) {
            val iterable = (Iterable<?>) object;
            val data = new HashMap<String, Object>();
            for (val item : iterable) {
                val file = (MultipartFile) item;
                data.put(file.getName(), file);
            }
            super.encode(data, MAP_STRING_WILDCARD, template);
        } else {
            super.encode(object, bodyType, template);
        }
    }

    private boolean isMultipartFileCollection (Object object) {
        if (!(object instanceof Iterable)) {
            return false;
        }
        Iterable iterable = (Iterable<?>) object;
        Iterator iterator = iterable.iterator();
        return iterator.hasNext() && iterator.next() instanceof MultipartFile;
    }
}
