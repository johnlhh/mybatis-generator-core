package org.mybatis.generator.codegen.mybatis3.javamapper.elements;

import org.mybatis.generator.api.dom.java.*;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by luohuahua on 18/8/25.
 */
public class CountByMapMethodGenerator extends AbstractJavaMapperMethodGenerator {

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = new Method();

        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("countByMap");

        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType("java.util.Map");

        importedTypes.add(parameterType);
        method.addParameter(new Parameter(parameterType, "params")); //$NON-NLS-1$

        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);

        addMapperAnnotations(interfaze, method);

        interfaze.addImportedTypes(importedTypes);
        interfaze.addMethod(method);
    }

    public CountByMapMethodGenerator() {
        super();
    }

    public void addMapperAnnotations(Interface interfaze, Method method) {
        return;
    }
}
