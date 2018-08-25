package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * Created by luohuahua on 18/8/25.
 */
public class CountByMapElementGenerator extends
        AbstractXmlElementGenerator {

    public CountByMapElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        addComment(parentElement);
        XmlElement answer = new XmlElement("select"); //$NON-NLS-1$

        answer.addAttribute(new Attribute(
                "id", "countByMap")); //$NON-NLS-1$
        answer.addAttribute(new Attribute("resultType", //$NON-NLS-1$
                "Integer"));

        String parameterType = "map";

        answer.addAttribute(new Attribute("parameterType", //$NON-NLS-1$
                parameterType));

        context.getCommentGenerator().addComment(parentElement);

        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) "); //$NON-NLS-1$

        answer.addElement(new TextElement(sb.toString()));

        sb.setLength(0);
        sb.append("from "); //$NON-NLS-1$
        sb.append(introspectedTable
                .getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        answer.addElement(getMapWhereClauseElement());

        parentElement.addElement(answer);
    }

    private void addComment(XmlElement parentElement){
        StringBuilder sb = new StringBuilder();
        sb.append("<!--")
                .append("统计查询")
                .append(introspectedTable.getRemarks()).append("个数")
                .append("--> ");
        parentElement.addElement(new TextElement(sb.toString()));
    }
}