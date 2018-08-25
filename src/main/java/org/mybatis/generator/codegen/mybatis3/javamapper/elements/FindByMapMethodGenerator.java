package org.mybatis.generator.codegen.mybatis3.javamapper.elements;

import org.mybatis.generator.api.dom.java.*;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by luohuahua on 18/8/25.
 */
public class FindByMapMethodGenerator extends AbstractJavaMapperMethodGenerator {

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = new Method();

        FullyQualifiedJavaType returnType = FullyQualifiedJavaType
                .getNewListInstance();
        FullyQualifiedJavaType listType = new FullyQualifiedJavaType(introspectedTable
                .getBaseRecordType());

        importedTypes.add(listType);
        importedTypes.add(returnType);

        returnType.addTypeArgument(listType);

        method.setReturnType(returnType);

        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("findByMap");

        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType("java.util.Map");

        importedTypes.add(parameterType);
        method.addParameter(new Parameter(parameterType, "params")); //$NON-NLS-1$

        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);

        addMapperAnnotations(interfaze, method);

        interfaze.addImportedTypes(importedTypes);
        interfaze.addMethod(method);
    }

    public FindByMapMethodGenerator() {
        super();
    }

    public void addMapperAnnotations(Interface interfaze, Method method) {
        return;
    }
}
