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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import org.bob.web.applications.smlite.engine.utils.Constants;
import org.bob.web.applications.smlite.engine.utils.SMliteDb;
import org.bob.web.applications.smlite.engine.utils.Utils;

/**
 * Class for the category representation.
 * 
 * @author roberto.gatti
 */
@NamedQueries({
    @NamedQuery(name = SMliteDb.NQ_CATEGORIES_BY_ID_NAME   , query = SMliteDb.NQ_CATEGORIES_BY_ID),
    @NamedQuery(name = SMliteDb.NQ_CATEGORIES_BY_TITLE_NAME, query = SMliteDb.NQ_CATEGORIES_BY_TITLE),
    @NamedQuery(name = SMliteDb.NQ_CATEGORIES_ALL_NAME     , query = SMliteDb.NQ_CATEGORIES_ALL)
})
@Entity
@Table( name = "categories" )
@XmlRootElement
public class CategoryBean extends BaseSMLiteBean {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SMliteDb.SQ_CATEGORIES_NAME)
    @SequenceGenerator(name = SMliteDb.SQ_CATEGORIES_NAME, 
                       sequenceName = SMliteDb.SQ_CATEGORIES_NAME, 
                       allocationSize = SMliteDb.DEFAULT_ALLOCATION_SEQUENCE_SIZE)
    @Column( name = "id" )
    private int id;
    
    @Column( name = "title" )
    private String title;
    
    @Column( name = "description" )
    private String description;
    
    @Column( name = "color" )
    private String color;
    
    @Column( name = "tmst_upd" )
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tmstUpd;
    
    @Column( name = "tmst_ins" )
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tmstIns;

    
    // <editor-fold desc="CTORs">
    
    public CategoryBean() {
        this.setId(Constants.CONST_DEFAULT_ID_VALUE);
        this.setColor(Constants.CONST_DEFAULT_COLOR_HEX_STRING);
        this.setTitle(Constants.CONST_DEFAULT_TITLE_VALUE);
        this.setDescription(Constants.CONST_DEFAULT_DESCRIPTION_VALUE);
        Date tmst = new Date();
        this.setTmstIns(tmst);
        this.setTmstUpd(tmst);
    }
    
    public CategoryBean(int id, String title, String color)
    {
        this.setId(Constants.CONST_DEFAULT_ID_VALUE);
        this.setColor(color);
        this.setTitle(title);
        this.setDescription(Constants.CONST_DEFAULT_DESCRIPTION_VALUE);
        Date tmst = new Date();
        this.setTmstIns(tmst);
        this.setTmstUpd(tmst);        
    }
    
    public CategoryBean(String title, String color, String description)
    {
        this.setId(Constants.CONST_DEFAULT_ID_VALUE);
        this.setColor(color);
        this.setTitle(title);
        this.setDescription(description);
        Date tmst = new Date();
        this.setTmstIns(tmst);
        this.setTmstUpd(tmst);
    }
    // </editor-fold>
    
    
    
    //<editor-fold desc="GETTER AND SETTERS">
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title.toUpperCase();
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }    
    
    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the tmstUpd
     */
    public Date getTmstUpd() {
        return tmstUpd;
    }

    /**
     * @param tmstUpd the tmstUpd to set
     */
    public void setTmstUpd(Date tmstUpd) {
        this.tmstUpd = tmstUpd;
    }

    /**
     * @return the tmstIns
     */
    public Date getTmstIns() {
        return tmstIns;
    }

    /**
     * @param tmstIns the tmstIns to set
     */
    public void setTmstIns(Date tmstIns) {
        this.tmstIns = tmstIns;
    }
    
    // </editor-fold>

    // <editor-fold desc="OBJECT OVERRIDDEN METHODS">
    @Override
    public String toString() {
        return    "{ " + 
                  " id: "           + this.getId() + "," + 
                  " title: '"       + this.getTitle() + "'," + 
                  " description: '" + this.getDescription() + "'," +
                  " color: '"       + this.getColor() + "," +
                  " tmst_ins: '"    + Utils.DF_FULL_DATE.format(this.getTmstIns()) + "', " + 
                  " tmst_upd: '"    + Utils.DF_FULL_DATE.format(this.getTmstUpd()) + "'" + 
                  "}"; 
    }

    /*@Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); 
    }*/

    @Override
    public boolean equals(Object o) {
        
        if ( o == null ) 
            return false;
        
        if ( ! ( o instanceof CategoryBean ) )
            return false;
        
        CategoryBean cat = ( CategoryBean ) o;
        
        /*System.out.println(" --------------------------------------------------------------------------------------------------------- ");
        System.out.println(" @@ EQUALS @@ Comparing this.hashCode = " + this.hashCode() + " to obj.hashCode = " + cat.hashCode() );
        System.out.println(" @@ EQUALS @@ The two object compared are: " + this.toString()  );
        System.out.println(" @@ |--                              with: " + cat.toString()  );
        System.out.println(" --------------------------------------------------------------------------------------------------------- ");*/
        
        if ( cat.getTitle() == null || ! cat.getTitle().equals(this.getTitle()) )
        {
            return false;
        }
            
        
        if ( cat.getDescription() == null || ! cat.getDescription().equals(this.getDescription()) )
        {
            return false;
        }
        
        if ( cat.getColor() == null || ! cat.getColor().equals(this.getColor()) )
        {
            return false;
        }
        
        //System.out.println(" ASSERTING " + this.hashCode() + " == " + cat.hashCode() );
        assert ( this.hashCode() == cat.hashCode() );
        //System.out.println("MATCH FOUND!");
        return true;
    }

    @Override
    public int hashCode() {
        
        int result = 17;
        
        /*result = 31 * result + (booleanField ? 1 : 0);                   // 1 bit   » 32-bit
        result = 31 * result + byteField;                                // 8 bits  » 32-bit 
        result = 31 * result + charField;                                // 16 bits » 32-bit
        result = 31 * result + shortField;                               // 16 bits » 32-bit
        result = 31 * result + intField;                                 // 32 bits » 32-bit
        result = 31 * result + (int)(longField ^ (longField >>> 32));    // 64 bits » 32-bit
        result = 31 * result + Float.floatToIntBits(floatField);         // 32 bits » 32-bit
        long doubleFieldBits = Double.doubleToLongBits(doubleField);     // 64 bits (double) » 64-bit (long) » 32-bit (int)
        result = 31 * result + (int)(doubleFieldBits ^ (doubleFieldBits >>> 32));*/
        
        result = 31 * result + this.getTitle().hashCode();
        result = 31 * result + this.getDescription().hashCode();
        result = 31 * result + this.getColor().hashCode();
        
        /*
            // var bits » 32-bit
        result = 31 * result + Arrays.hashCode(arrayField);
            // var bits » 32-bit (non-nullable)   
        result = 31 * result + referenceField.hashCode();
            // var bits » 32-bit (nullable)   
        result = 31 * result + (nullableReferenceField == null ? 0 : nullableReferenceField.hashCode()); 
        */
                
        return result;
    }

    //</editor-fold>

}
