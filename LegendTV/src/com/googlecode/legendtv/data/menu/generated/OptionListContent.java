//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.4-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.08.19 at 05:00:31 PM EDT 
//


package com.googlecode.legendtv.data.menu.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OptionListContent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OptionListContent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dynamicContent" type="{http://www.googlecode.com/p/legendtv/Menus}DynamicContent" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="option" type="{http://www.googlecode.com/p/legendtv/Menus}Option" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OptionListContent", propOrder = {
    "contentGeneratorReferences",
    "options"
})
public class OptionListContent {

    @XmlElement(name = "dynamicContent")
    protected List<ContentGeneratorReference> contentGeneratorReferences;
    @XmlElement(name = "option")
    protected List<Option> options;

    /**
     * Dynamic content generator references, which are used to add additional
     * options to the list at run-time.Gets the value of the contentGeneratorReferences property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contentGeneratorReferences property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContentGeneratorReferences().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContentGeneratorReference }
     * 
     * 
     */
    public List<ContentGeneratorReference> getContentGeneratorReferences() {
        if (contentGeneratorReferences == null) {
            contentGeneratorReferences = new ArrayList<ContentGeneratorReference>();
        }
        return this.contentGeneratorReferences;
    }

    /**
     * The static options in the list.Gets the value of the options property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the options property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOptions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Option }
     * 
     * 
     */
    public List<Option> getOptions() {
        if (options == null) {
            options = new ArrayList<Option>();
        }
        return this.options;
    }

}
