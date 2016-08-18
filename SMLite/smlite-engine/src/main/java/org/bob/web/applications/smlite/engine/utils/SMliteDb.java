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
package org.bob.web.applications.smlite.engine.utils;

/**
 *
 * @author roberto.gatti
 */
public class SMliteDb {
    
    public static final String SQ_CATEGORIES_NAME               = "categories_id_seq";
    public static final int    DEFAULT_ALLOCATION_SEQUENCE_SIZE = 1;
    
    public static final String NQ_CATEGORIES_ALL           = "from CategoryBean";
    public static final String NQ_CATEGORIES_ALL_NAME      = "findAllCategories";
    
    public static final String NQ_CATEGORIES_BY_ID         = "from CategoryBean c where c.id = :id";
    public static final String NQ_CATEGORIES_BY_ID_NAME    = "findCategoriesById";
    
    public static final String NQ_CATEGORIES_BY_TITLE      = "from CategoryBean c where c.title = :title";
    public static final String NQ_CATEGORIES_BY_TITLE_NAME = "findCategoriesByTitle";
    
}
