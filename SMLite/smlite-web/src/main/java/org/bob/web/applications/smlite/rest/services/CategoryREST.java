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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.bob.web.applications.smlite.engine.MainTest;
import org.bob.web.applications.smlite.engine.beans.CategoryBean;
import org.bob.web.applications.smlite.engine.managers.AbstractManager;
import org.bob.web.applications.smlite.rest.beans.RestResult;
import org.bob.web.applications.smlite.web.spring.utils.ApplicationContextProvider;

/**
 *
 * @author roberto.gatti
 */
@Path( "/categories" )
public class CategoryREST {
    
    private AbstractManager<CategoryBean> categoriesManager;
    
    @GET
    //@Consumes({MediaType.APPLICATION_JSON } )
    @Produces({MediaType.APPLICATION_JSON } )
    public RestResult getCategories() {
        if ( this.categoriesManager == null )
            this.setCategoriesManager();
        
        List<CategoryBean> categories = new ArrayList<CategoryBean>();
        RestResult result = new RestResult();
        try
        {
            categories = this.categoriesManager.get(null);
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
    //@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public RestResult deleteCategory(@PathParam("id") String _id)
    {
        RestResult result = new RestResult();
        try
        {
            if ( _id == null )
                throw new NullPointerException("The id of the updated category must be specified in the url (e. g. '.../rest/categories/1')!");
            int id = Integer.parseInt(_id);            
            MainTest.getInstance().deleteCategory(id);
            result.setHttpCode(200);
            result.setMessage("Category successfully deleted!");
            result.setBody(null);            
        }
        catch ( NumberFormatException e )
        {
            result.setHttpCode(500);
            result.setMessage("Id of category is not a number: '" + _id + "'!");            
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
    @Path("{id}")
    public RestResult updateCategory(CategoryBean bean, @PathParam("id") String id)
    {
        RestResult result = new RestResult();
        try
        {
            if ( id == null )
                throw new NullPointerException("The id of the updated category must be specified in the url (e. g. '.../rest/categories/1')!");
            Integer.parseInt(id);
            bean = MainTest.getInstance().updateCategory(bean);
            result.setHttpCode(200);
            result.setMessage("Category successfully updated!");
            result.setBody(bean);            
        }
        catch ( NumberFormatException e)
        {
            result.setHttpCode(500);
            result.setMessage("Id of category is not a number: '" + id + "'!");
        }
        catch ( Throwable e )
        {
            result.setHttpCode(500);
            result.setMessage(e.getMessage());
        }
        return result;
    }
    
    public void setCategoriesManager()
    {
        this.categoriesManager = (AbstractManager) ApplicationContextProvider
                                        .getApplicationContext()
                                        .getBean("categoriesManager");
    }
}
