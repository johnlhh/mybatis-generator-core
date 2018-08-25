package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * Created by luohuahua on 18/8/25.
 */
public class FindByMapElementGenerator extends
        AbstractXmlElementGenerator {

    public FindByMapElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        addComment(parentElement);
        XmlElement answer = new XmlElement("select"); //$NON-NLS-1$

        answer.addAttribute(new Attribute(
                "id", "findByMap")); //$NON-NLS-1$
        answer.addAttribute(new Attribute("resultMap", //$NON-NLS-1$
                introspectedTable.getBaseResultMapId()));

        String parameterType = "map";

        answer.addAttribute(new Attribute("parameterType", //$NON-NLS-1$
                parameterType));

        context.getCommentGenerator().addComment(parentElement);

        StringBuilder sb = new StringBuilder();
        sb.append("select "); //$NON-NLS-1$

        answer.addElement(new TextElement(sb.toString()));
        answer.addElement(getBaseColumnListElement());

        sb.setLength(0);
        sb.append("from "); //$NON-NLS-1$
        sb.append(introspectedTable
                .getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        answer.addElement(getMapWhereClauseElement());


        XmlElement ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "offSet != null and rowsPerPage != null")); //$NON-NLS-1$ //$NON-NLS-2$
        TextElement textElement = new TextElement(" limit #{offSet} , #{rowsPerPage}");
        ifElement.addElement(textElement);
        answer.addElement(ifElement);

        parentElement.addElement(answer);
    }

    private void addComment(XmlElement parentElement){
        StringBuilder sb = new StringBuilder();
        sb.append("<!--")
                .append("分页查询")
                .append(introspectedTable.getRemarks())
                .append("--> ");
        parentElement.addElement(new TextElement(sb.toString()));
    }
}