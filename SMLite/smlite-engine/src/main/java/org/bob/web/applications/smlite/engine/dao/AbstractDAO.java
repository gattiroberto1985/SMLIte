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
package org.bob.web.applications.smlite.engine.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.bob.web.applications.smlite.engine.exceptions.SMLiteDAOException;

/**
 * Interface for the DAO models.
 * 
 * @author roberto.gatti
 */
public interface AbstractDAO<SMLiteObjType> {
    
    
    /**
     * Method to insert a new Bean (returns the new id).
     * 
     * @param bean A BaseSMLiteBean object to insert.
     * 
     * @return the id of the new object.
     * 
     * @throws SMLiteDAOException if something goes wrong
     */
    @Transactional
    public abstract SMLiteObjType insert(SMLiteObjType bean) throws SMLiteDAOException;
    
    
    /**
     * Method to update an object. 
     * 
     * @param bean The bean to update. It should have all the values updated 
     *             except for the id, which should be the old one.
     * 
     * @return the number of rows updated.
     * 
     * @throws SMLiteDAOException if something goes wrong
     */
    @Transactional
    public abstract SMLiteObjType update(SMLiteObjType bean) throws SMLiteDAOException;
    
    
    /**
     * Method to search an object. Specify <code>null</null> as parameter if 
     * you want to retreive all the objects of that type.
     * 
     * @param bean the bean to search
     * 
     * @return a list with all the bean which satisfies the criteria.
     * 
     * @throws SMLiteDAOException if something goes wrong.
     */
    @Transactional
    public abstract List<SMLiteObjType> get(SMLiteObjType bean) throws SMLiteDAOException;
    
    
    /**
     * Method to delete a bean. 
     * 
     * @param bean the bean to delete. Fields with <code>null</code> values are
     *             treated as "%".
     * 
     * @return the number of beans deleted.
     * 
     * @throws SMLiteDAOException if something goes wrong.
     */
    @Transactional
    public abstract void delete(SMLiteObjType bean) throws SMLiteDAOException;
    
}
