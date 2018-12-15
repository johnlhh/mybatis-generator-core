package org.mybatis.generator.codegen.mybatis3;

import org.mybatis.generator.api.IntrospectedColumn;

/**
 * Created by luohuahua on 18/10/13.
 */
public class MyBatis3ColumnUtilities {

    public static boolean isFilterColumn(IntrospectedColumn introspectedColumn){
        if(introspectedColumn.isIdentity() || introspectedColumn.isSequenceColumn()){
            return true;
        }
        if(introspectedColumn.getDefaultValue() != null && introspectedColumn.getDefaultValue().toUpperCase().equals("CURRENT_TIMESTAMP")){
            return true;
        }
        return false;
    }
}
