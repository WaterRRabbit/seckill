package com.hg.seckill.util;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * Created by YE
 * 2019-04-17 22:50
 */
public class SerializerUtil {

    /**
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> byte[] serializer(T t){
        Schema schema = RuntimeSchema.getSchema(t.getClass());
        return ProtostuffIOUtil.toByteArray(t, schema,
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
    }

    /**
     *
     * @param bytes
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T deserializer(byte[] bytes, Class<T> clazz){
        T t = null;
        try{
            t = clazz.newInstance();
            Schema schema = RuntimeSchema.getSchema(clazz);
            ProtostuffIOUtil.mergeFrom(bytes, t, schema);
        }catch (InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }
        return t;
    }
}
