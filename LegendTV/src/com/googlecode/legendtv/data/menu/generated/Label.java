//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.4-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.08.19 at 05:00:31 PM EDT 
//


package com.googlecode.legendtv.data.menu.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A control that displays text.
 * 
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Label", propOrder = {
    "content"
})
public class Label
    extends Control
{

    @XmlElement(required = true)
    protected StringContent content;

    /**
     * The content of this label.
     * 
     * @return
     *     possible object is
     *     {@link StringContent }
     *     
     */
    public StringContent getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringContent }
     *     
     */
    public void setContent(StringContent value) {
        this.content = value;
    }

}