//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation, v2.2.11
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.04.04 at 08:02:09 AM BST
//

package uk.gov.ons.census.fwmtadapter.model.dto.field;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Java class for Priority.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <p>
 *
 * <pre>
 * &lt;simpleType name="Priority"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="highest"/&gt;
 *     &lt;enumeration value="higher"/&gt;
 *     &lt;enumeration value="medium"/&gt;
 *     &lt;enumeration value="lower"/&gt;
 *     &lt;enumeration value="lowest"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "Priority")
@XmlEnum
public enum Priority {
  @XmlEnumValue("highest")
  HIGHEST("highest"),
  @XmlEnumValue("higher")
  HIGHER("higher"),
  @XmlEnumValue("medium")
  MEDIUM("medium"),
  @XmlEnumValue("lower")
  LOWER("lower"),
  @XmlEnumValue("lowest")
  LOWEST("lowest");
  private final String value;

  Priority(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static Priority fromValue(String v) {
    for (Priority c : Priority.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }
}
