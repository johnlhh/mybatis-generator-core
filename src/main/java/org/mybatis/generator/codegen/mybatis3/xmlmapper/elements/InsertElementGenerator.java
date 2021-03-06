/**
 *    Copyright 2006-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3ColumnUtilities;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.GeneratedKey;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class InsertElementGenerator extends AbstractXmlElementGenerator {

    private boolean isSimple;

    public InsertElementGenerator(boolean isSimple) {
        super();
        this.isSimple = isSimple;
    }




    @Override
    public void addElements(XmlElement parentElement) {
        addComment(parentElement);
        XmlElement answer = new XmlElement("insert"); //$NON-NLS-1$

        answer.addAttribute(new Attribute(
                "id", introspectedTable.getInsertStatementId())); //$NON-NLS-1$

        FullyQualifiedJavaType parameterType;
        if (isSimple) {
            parameterType = new FullyQualifiedJavaType(
                    introspectedTable.getBaseRecordType());
        } else {
            parameterType = introspectedTable.getRules()
                    .calculateAllFieldsClass();
        }

        answer.addAttribute(new Attribute("parameterType", //$NON-NLS-1$
                parameterType.getFullyQualifiedName()));

        context.getCommentGenerator().addComment(parentElement);

        GeneratedKey gk = introspectedTable.getGeneratedKey();
        /*if (gk != null) {
            IntrospectedColumn introspectedColumn = introspectedTable
                    .getColumn(gk.getColumn());
            // if the column is null, then it's a configuration error. The
            // warning has already been reported
            if (introspectedColumn != null) {
                if (gk.isJdbcStandard()) {
                    answer.addAttribute(new Attribute(
                            "useGeneratedKeys", "true")); //$NON-NLS-1$ //$NON-NLS-2$
                    answer.addAttribute(new Attribute(
                            "keyProperty", introspectedColumn.getJavaProperty())); //$NON-NLS-1$
                } else {
                    answer.addElement(getSelectKey(introspectedColumn, gk));
                }
            }
        }*/

        answer.addAttribute(new Attribute(
                "useGeneratedKeys", "true")); //$NON-NLS-1$ //$NON-NLS-2$
        answer.addAttribute(new Attribute(
                "keyProperty", introspectedTable.getColumn(gk.getColumn()).getJavaProperty())); //$NON-NLS-1$

        StringBuilder insertClause = new StringBuilder();
        StringBuilder valuesClause = new StringBuilder();

        insertClause.append("insert into "); //$NON-NLS-1$
        insertClause.append(introspectedTable
                .getFullyQualifiedTableNameAtRuntime());
        insertClause.append(" ("); //$NON-NLS-1$

        valuesClause.append("values ("); //$NON-NLS-1$

        List<String> valuesClauses = new ArrayList<String>();
        List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
        for (int i = 0; i < columns.size(); i++) {
            IntrospectedColumn introspectedColumn = columns.get(i);
            if (MyBatis3ColumnUtilities.isFilterColumn(introspectedColumn)) {
                // cannot set values on identity fields
                continue;
            }
            insertClause.append(introspectedColumn.getActualColumnName());
            valuesClause.append(MyBatis3FormattingUtilities
                    .getParameterClauseClear(introspectedColumn));
            if (i + 1 < columns.size()) {

                    insertClause.append(", "); //$NON-NLS-1$
                    valuesClause.append(", "); //$NON-NLS-1$
            }
            if (valuesClause.length() > 80) {
                answer.addElement(new TextElement(insertClause.toString()));
                insertClause.setLength(0);
                OutputUtilities.xmlIndent(insertClause, 1);

                valuesClauses.add(valuesClause.toString());
                valuesClause.setLength(0);
                OutputUtilities.xmlIndent(valuesClause, 1);
            }
        }
        if(insertClause.lastIndexOf(",") == insertClause.length()-1){
            insertClause.deleteCharAt(insertClause.length()-1);
        }
        if(valuesClause.lastIndexOf(",") == valuesClause.length()-1){
            valuesClause.deleteCharAt(valuesClause.length()-1);
        }

        insertClause.append(')');
        answer.addElement(new TextElement(insertClause.toString()));

        valuesClause.append(')');
        valuesClauses.add(valuesClause.toString());

        for (String clause : valuesClauses) {
            answer.addElement(new TextElement(clause));
        }

        if (context.getPlugins().sqlMapInsertElementGenerated(answer,
                introspectedTable)) {
            parentElement.addElement(answer);
        }
    }

    private void addComment(XmlElement parentElement){
        StringBuilder sb = new StringBuilder();
        sb.append("<!--")
                .append("新增")
                .append(introspectedTable.getRemarks())
                .append("--> ");
        parentElement.addElement(new TextElement(sb.toString()));
    }
}
