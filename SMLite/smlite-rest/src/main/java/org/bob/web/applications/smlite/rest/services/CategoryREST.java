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
package org.bob.web.applications.smlite.rest.services;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.bob.web.applications.smlite.engine.MainTest;
import org.bob.web.applications.smlite.engine.beans.CategoryBean;
import org.bob.web.applications.smlite.rest.beans.RestResult;

/**
 *
 * @author roberto.gatti
 */
@Path( "/categories" )
public class CategoryREST {
    
    @GET
    //@Consumes({MediaType.APPLICATION_JSON } )
    @Produces({MediaType.APPLICATION_JSON } )
    public RestResult getCategories() {
        List<CategoryBean> categories = new ArrayList<CategoryBean>();
        RestResult result = new RestResult();
        try
        {
            categories = MainTest.getInstance().getCategories();
            result.setHttpCode(200);
            result.setMessage("Categories retreived!");
            result.setBody(categories);
        }
        catch ( Throwable t )
        {
            result.setHttpCode(500);
            result.setMessage(t.getMessage());
            //t.printStackTrace();
        }
        return result;
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public RestResult addCategory(CategoryBean bean)
    {
        RestResult result = new RestResult();
        try
        {
            System.out.println("Trying to insert category: " + bean.toString() );
            bean = MainTest.getInstance().addCategory(bean);
            result.setHttpCode(200);
            result.setMessage("Category successfully created!");
            result.setBody(bean);
        }
        catch ( Throwable e )
        {
            result.setHttpCode(500);
            result.setMessage(e.getMessage());
        }
        return result;
    }
    
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public RestResult deleteCategory(CategoryBean bean)
    {
        RestResult result = new RestResult();
        try
        {
            MainTest.getInstance().deleteCategory(bean);
            result.setHttpCode(200);
            result.setMessage("Category successfully deleted!");
            result.setBody(bean);            
        }
        catch ( Throwable e )
        {
            result.setHttpCode(500);
            result.setMessage(e.getMessage());
        }
        return result;
    }    
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public RestResult updateCategory(CategoryBean bean)
    {
        RestResult result = new RestResult();
        try
        {
            bean = MainTest.getInstance().updateCategory(bean);
            result.setHttpCode(200);
            result.setMessage("Category successfully updated!");
            result.setBody(bean);            
        }
        catch ( Throwable e )
        {
            result.setHttpCode(500);
            result.setMessage(e.getMessage());
        }
        return result;
    }
    
}
