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
package org.bob.web.applications.smlite.rest;

import javax.ws.rs.Path;
import org.bob.web.applications.smlite.rest.beans.RestResult;
import org.bob.web.applications.smlite.rest.config.GSONMessageBodyHandler;
import org.bob.web.applications.smlite.rest.services.CategoryREST;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author roberto.gatti
 */
@Path("/rest")
public class RestRegister extends ResourceConfig {
    
    /**
     * Register JAX-RS application components.
     */	
    public RestRegister()
    {
        //packages("org.bob.web.applications.smlite.rest");
        //register(GensonMessageBodyHandler.class);
        register(LoggingFeature.class);        
        register(GSONMessageBodyHandler.class);
        register(CategoryREST.class);
        register(RestResult.class);
    }
}
