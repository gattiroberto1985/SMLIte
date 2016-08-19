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
package org.bob.web.applications.smlite.engine.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bob.web.applications.smlite.engine.exceptions.SMLiteDAOException;
import org.bob.web.applications.smlite.engine.exceptions.SMLiteEngineException;
import org.bob.web.applications.smlite.engine.managers.AbstractManager;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;

/**
 *
 * @author roberto.gatti
 */
@Rollback( true )
public class CategoryBeanTest {
    
    public CategoryBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testCategoryBeanClass() throws SMLiteEngineException
    {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");	
        AbstractManager<CategoryBean> cm = (AbstractManager<CategoryBean>) ctx.getBean("categoriesManager");
        Set<CategoryBean> categories = new HashSet<>();
        categories.add( new CategoryBean("VERDURA"         , "#5bb800", "Verdura") );
        categories.add( new CategoryBean("FRUTTA"          , "#5bb800", "Frutta") );
        categories.add( new CategoryBean("UOVA E LATTICINI", "#5bb800", "Uova, latte e derivati") );
        categories.add( new CategoryBean("CARNE"           , "#5bb800", "Carne e affini") );
        categories.add( new CategoryBean("PESCE"           , "#5bb800", "Pesce e affini") );
        categories.add( new CategoryBean("IGIENE CASA"     , "#5bb800", "Prodotti di igiene per la casa") );
        categories.add( new CategoryBean("IGIENE PERSONALE", "#5bb800", "Prodotti per l'igiene personale") );
        categories.add( new CategoryBean("SURGELATI"       , "#5bb800", "Surgelati") );
        categories.add( new CategoryBean("PANE E FARINA"   , "#5bb800", "Pane, farine, panificati vari") );
        categories.add( new CategoryBean("DOLCI"           , "#5bb800", "Dolci freschi e gelati, preparati per dolci") );
        categories.add( new CategoryBean("PRIMA COLAZIONE" , "#5bb800", "Biscotti e cose per la prima colazione") );
        categories.add( new CategoryBean("BIBITE"          , "#5bb800", "Succhi, acqua, birre e bibite in generale") );
        categories.add( new CategoryBean("ALTRO"           , "#5bb800", "Articoli di altro genere (insaporitori, olio, aceto, ...)") );
        try
        {
            List<CategoryBean> existingCategories = cm.get(null);
            for ( CategoryBean newCB : categories )
            {
                if ( ! existingCategories.contains( newCB ) )
                {
                    System.out.println("@@@ TEST: INSERTING category: " + "\n" + " |-- " + newCB.toString() + ". . . ");
                    cm.insert( newCB );
                }
            }
        }
        catch ( SMLiteDAOException ex )
        {
            System.out.println("ERROR: " + ex.getMessage() + ". \nFollowing stacktrace: ");
        }
    }
    
    @Test
    @Ignore("Not needed, works fine!")
    public void testDb() throws SMLiteEngineException
    {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");	
        AbstractManager<CategoryBean> cm = (AbstractManager<CategoryBean>) ctx.getBean("categoriesManager");
        CategoryBean cat = new CategoryBean(-1, "CATEGORIA " + new Date(), "0x000000");
        
        try
        {        
            System.out.println("@@@ TEST: Adding fake category: " + cat.toString() + " ... ");
            cat = cm.insert( cat );
            
            System.out.println("@@@ TEST: Check inserted category: " );
            List<CategoryBean> _cat = cm.get( cat );
            if ( _cat == null || _cat.size() != 1 )
                throw new SMLiteEngineException("ERROR: category is null or duplicate!");
            System.out.println( "@@@ TEST: db says: " + _cat.get(0).toString() );
            
            cat.setColor("0xFF00FF");
            System.out.println("@@@ TEST: Updating fake category: " + cat.toString() + " ..." );
            cat = cm.update(cat);            
            System.out.println("@@@ TEST: Check updated category: " );
            _cat = cm.get( cat );
            if ( _cat == null || _cat.size() != 1 )
                throw new SMLiteEngineException("ERROR: category is null or duplicate!");
            System.out.println( "@@@ TEST: db says: " + _cat.get(0).toString() );
            
            System.out.println("@@@ TEST: deleting fake category . . .");
            cm.delete(cat.getId());   
        }
        catch ( SMLiteDAOException ex )
        {
            System.out.println("ERROR: " + ex.getMessage() + ". \nFollowing stacktrace: ");
        }            
    }

    /**
     * Test of getId method, of class CategoryBean.
     */
    @Ignore( " @@@@ TEST ]@@@@ CategoryBean @@@@ Not defined!")
    @Test
    public void testGetId() {
        System.out.println("getId");
        CategoryBean instance = new CategoryBean();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class CategoryBean.
     */
    @Test
    @Ignore( " @@@@ TEST ]@@@@ CategoryBean @@@@ Not defined!")
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        CategoryBean instance = new CategoryBean();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitle method, of class CategoryBean.
     */
    @Test
    @Ignore( " @@@@ TEST ]@@@@ CategoryBean @@@@ Not defined!")
    public void testGetTitle() {
        System.out.println("getTitle");
        CategoryBean instance = new CategoryBean();
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTitle method, of class CategoryBean.
     */
    @Test
    @Ignore( " @@@@ TEST ]@@@@ CategoryBean @@@@ Not defined!")
    public void testSetTitle() {
        System.out.println("setTitle");
        String title = "";
        CategoryBean instance = new CategoryBean();
        instance.setTitle(title);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class CategoryBean.
     */
    @Test
    @Ignore( " @@@@ TEST ]@@@@ CategoryBean @@@@ Not defined!")
    public void testGetDescription() {
        System.out.println("getDescription");
        CategoryBean instance = new CategoryBean();
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescription method, of class CategoryBean.
     */
    @Test
    @Ignore( " @@@@ TEST ]@@@@ CategoryBean @@@@ Not defined!")
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "";
        CategoryBean instance = new CategoryBean();
        instance.setDescription(description);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColor method, of class CategoryBean.
     */
    @Test
    @Ignore( " @@@@ TEST ]@@@@ CategoryBean @@@@ Not defined!")
    public void testGetColor() {
        System.out.println("getColor");
        CategoryBean instance = new CategoryBean();
        String expResult = "";
        String result = instance.getColor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setColor method, of class CategoryBean.
     */
    @Test
    @Ignore( " @@@@ TEST ]@@@@ CategoryBean @@@@ Not defined!")
    public void testSetColor() {
        System.out.println("setColor");
        String color = "";
        CategoryBean instance = new CategoryBean();
        instance.setColor(color);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTmstUpd method, of class CategoryBean.
     */
    @Test
    @Ignore( " @@@@ TEST ]@@@@ CategoryBean @@@@ Not defined!")
    public void testGetTmstUpd() {
        System.out.println("getTmstUpd");
        CategoryBean instance = new CategoryBean();
        Date expResult = null;
        Date result = instance.getTmstUpd();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTmstUpd method, of class CategoryBean.
     */
    @Test
    @Ignore( " @@@@ TEST ]@@@@ CategoryBean @@@@ Not defined!")
    public void testSetTmstUpd() {
        System.out.println("setTmstUpd");
        Date tmstUpd = null;
        CategoryBean instance = new CategoryBean();
        instance.setTmstUpd(tmstUpd);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTmstIns method, of class CategoryBean.
     */
    @Test
    @Ignore( " @@@@ TEST ]@@@@ CategoryBean @@@@ Not defined!")
    public void testGetTmstIns() {
        System.out.println("getTmstIns");
        CategoryBean instance = new CategoryBean();
        Date expResult = null;
        Date result = instance.getTmstIns();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTmstIns method, of class CategoryBean.
     */
    @Test
    @Ignore( " @@@@ TEST ]@@@@ CategoryBean @@@@ Not defined!")
    public void testSetTmstIns() {
        System.out.println("setTmstIns");
        Date tmstIns = null;
        CategoryBean instance = new CategoryBean();
        instance.setTmstIns(tmstIns);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
