package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.Iterator;

/**
 * Created by luohuahua on 18/8/25.
 */
public class MapWhereClauseElementGenerator extends AbstractXmlElementGenerator {

    public MapWhereClauseElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        addComment(parentElement);
        XmlElement answer = new XmlElement("sql"); //$NON-NLS-1$

        answer.addAttribute(new Attribute("id", //$NON-NLS-1$
                "Map_Where_Clause"));

        context.getCommentGenerator().addComment(parentElement);
        XmlElement whereElement = new XmlElement("where"); //$NON-NLS-1$
        answer.addElement(whereElement);

        StringBuilder sb = new StringBuilder();
        Iterator<IntrospectedColumn> iter = introspectedTable
                .getNonBLOBColumns().iterator();
        while (iter.hasNext()) {
            IntrospectedColumn introspectedColumn = iter.next();
            String propertyName = introspectedColumn.getJavaProperty();
            XmlElement ifElement = new XmlElement("if"); //$NON-NLS-1$
            ifElement.addAttribute(new Attribute("test", propertyName + " != null")); //$NON-NLS-1$ //$NON-NLS-2$
            TextElement textElement = new TextElement(" and " + introspectedColumn.getActualColumnName() + " = #{" + propertyName + "}");
            ifElement.addElement(textElement);
            whereElement.addElement(ifElement);
        }
        parentElement.addElement(answer);
    }

    private void addComment(XmlElement parentElement) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!--")
                .append("查询条件语句")
                .append("--> ");
        parentElement.addElement(new TextElement(sb.toString()));
    }
}

