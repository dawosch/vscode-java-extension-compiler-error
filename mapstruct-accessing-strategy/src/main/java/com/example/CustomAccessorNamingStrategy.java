package com.example;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeKind;

import org.mapstruct.ap.spi.DefaultAccessorNamingStrategy;
import org.mapstruct.ap.spi.util.IntrospectorUtils;

public class CustomAccessorNamingStrategy extends DefaultAccessorNamingStrategy {

    @Override
    public boolean isGetterMethod(ExecutableElement method) {
        if (!method.getParameters().isEmpty()) {
            // If the method has parameters it can't be a getter
            return false;
        }
        String methodName = method.getSimpleName().toString();

        boolean isNonBooleanGetterName = methodName.startsWith("get") && methodName.length() > 3 &&
                method.getReturnType().getKind() != TypeKind.VOID;

        boolean isBooleanGetterName = methodName.startsWith("is") && methodName.length() > 2;
        boolean returnTypeIsBoolean = method.getReturnType().getKind() == TypeKind.BOOLEAN ||
                "java.lang.Boolean".equals(getQualifiedName(method.getReturnType()));
        boolean isFluentGetter = methodName.length() >= 2 && method.getReturnType().getKind() != TypeKind.VOID
                && !methodName.startsWith("set") && !methodName.startsWith("get") && !methodName.startsWith("with")
                && !methodName.startsWith("build")
                && !methodName.startsWith("is") && !methodName.startsWith("from");

        return isNonBooleanGetterName || (isBooleanGetterName && returnTypeIsBoolean) || isFluentGetter;

    }

    @Override
    public String getPropertyName(ExecutableElement getterOrSetterMethod) {
        String methodName = getterOrSetterMethod.getSimpleName().toString();
        if (isFluentSetter(getterOrSetterMethod)) {
            if (methodName.startsWith("set")
                    && methodName.length() > 3
                    && Character.isUpperCase(methodName.charAt(3))) {
                return IntrospectorUtils.decapitalize(methodName.substring(3));
            } else {
                return methodName;
            }
        }
        if ((methodName.startsWith("get") || methodName.startsWith("set")) && methodName.length() > 3) {
            return IntrospectorUtils.decapitalize(methodName.substring(3));
        } else if (methodName.startsWith("is") && methodName.length() > 3) {
            return IntrospectorUtils.decapitalize(methodName.substring(2));
        } else if (methodName.startsWith("with") && methodName.length() > 4) {
            return IntrospectorUtils.decapitalize(methodName.substring(4));
        }
        return IntrospectorUtils.decapitalize(methodName);
    }
}
