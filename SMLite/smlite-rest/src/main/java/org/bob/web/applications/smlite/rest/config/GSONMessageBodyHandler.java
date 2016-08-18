/*
 * The MIT License
 *
 * Copyright 2016 roberto.gatti.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.bob.web.applications.smlite.rest.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author roberto.gatti
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GSONMessageBodyHandler<JsonType> implements MessageBodyWriter<JsonType>,
        MessageBodyReader<JsonType> {

    private static final String UTF_8 = "UTF-8";

    private Gson gson;

    public GSONMessageBodyHandler()
    {
        //System.out.println("******************************** CONSTRUCTOR **********************");
    }
    
    //Customize the gson behavior here
    private Gson getGson() {
        //System.out.println("******************************** getGson **********************");
        if (gson == null) {
            final GsonBuilder gsonBuilder = new GsonBuilder();
            gson = gsonBuilder.disableHtmlEscaping()
                    //.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CAMEL_CASE)
                    .setPrettyPrinting()
                    .serializeNulls()
                    //.setDateFormat("dd/MM/yyyy HH:mm:SS.s")
                    .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                    .create();
        }
        return gson;
    }

    @Override
    public boolean isReadable(Class<?> type, Type genericType,
            java.lang.annotation.Annotation[] annotations, MediaType mediaType) {
        //System.out.println("******************************** READABLE **********************");
        return true;
    }

    @Override
    public JsonType readFrom(Class<JsonType> type, Type genericType,
            Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, String> httpHeaders, InputStream entityStream) {
        //System.out.println("******************************** ReadFrom **********************");
        InputStreamReader streamReader = null;
        try {
            streamReader = new InputStreamReader(entityStream, UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            Type jsonType;
            if (type.equals(genericType)) {
                jsonType = type;
            } else {
                jsonType = genericType;
            }
            return getGson().fromJson(streamReader, jsonType);
        } finally {
            try {
                streamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        //System.out.println("******************************** Writable **********************");
        return true;
    }

    @Override
    public long getSize(JsonType object, Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        //System.out.println("******************************** getSize **********************");
        return -1;
    }

    @Override
    public void writeTo(JsonType object, Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, Object> httpHeaders,
            OutputStream entityStream) throws IOException,
            WebApplicationException {
        //System.out.println("******************************** WRITEtO **********************");
        OutputStreamWriter writer = new OutputStreamWriter(entityStream, UTF_8);
        try {
            Type jsonType;
            if (type.equals(genericType)) {
                jsonType = type;
            } else {
                jsonType = genericType;
            }
            getGson().toJson(object, jsonType, writer);
        } finally {
            writer.close();
        }
    }
}
