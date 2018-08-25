/**
 * Copyright 2006-2015 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mybatis.generator.internal;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * 生成数据库表中comments字段
 *
 * @author guooo
 */
public class CustomeCommentGenerator extends DefaultCommentGenerator {

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        // 生成方法注释
        method.addJavaDocLine("/**");
        String method_name = method.getName();
        String tableRemarks = introspectedTable.getRemarks();
        if ("selectByExample".equals(method_name)) {
            method.addJavaDocLine(" * 根据条件查询" + tableRemarks + "列表");
        } else if (method_name.startsWith("updateByExample")) {
            method.addJavaDocLine(" * 选择性更新" + tableRemarks);
        } else if ("updateByPrimaryKeySelective".equals(method_name)) {
            method.addJavaDocLine(" * 根据主键来更新部分" + tableRemarks + "信息");
        } else if ("deleteByPrimaryKey".equals(method_name)) {
            method.addJavaDocLine(" * 根据主键删除" + tableRemarks);
        } else if ("insert".equals(method_name)) {
            method.addJavaDocLine(" * 新增" + tableRemarks);
        } else if ("selectByPrimaryKey".equals(method_name)) {
            method.addJavaDocLine(" * 根据主键获取" + tableRemarks);
        } else if ("updateByPrimaryKey".equals(method_name)) {
            method.addJavaDocLine(" * 根据主键更新" + tableRemarks);
        } else if ("selectAll".equals(method_name)) {
            method.addJavaDocLine(" * 获取全部" + tableRemarks);
        } else if ("countByExample".equals(method_name)) {
            method.addJavaDocLine(" * 根据条件统计" + tableRemarks + "个数");
        } else if ("insertSelective".equals(method_name)) {
            method.addJavaDocLine(" * 插入数据库记录");
        } else if ("countByMap".equals(method_name)) {
            method.addJavaDocLine(" * 根据条件统计" + tableRemarks + "个数");
        } else if ("findByMap".equals(method_name)) {
            method.addJavaDocLine(" * 根据条件分页查询" + tableRemarks);
        }

        method.addJavaDocLine(" *");
        List<Parameter> parameterList = method.getParameters();
        String paramterName;
        for (Parameter parameter : parameterList) {
            paramterName = parameter.getName();
            method.addJavaDocLine(" * @param " + paramterName);
        }
        method.addJavaDocLine(" */");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
        // 添加字段注释
        StringBuffer sb = new StringBuffer();
        field.addJavaDocLine("/**");
        if (introspectedColumn.getRemarks() != null)
            field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
        sb.append(" * 表 : ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        field.addJavaDocLine(sb.toString());
        field.addJavaDocLine(" * 对应字段 : " + introspectedColumn.getActualColumnName());
        // addJavadocTag(field, false);
        field.addJavaDocLine(" */");
    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {

    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
        // set方法注释

    }

    @Override
    public void addComment(XmlElement xmlElement) {

        //$NON-NLS-
    }
}
