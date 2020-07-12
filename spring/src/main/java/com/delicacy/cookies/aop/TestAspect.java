package com.delicacy.cookies.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.stereotype.Component;

/**
 * 切入点表达式参考：https://www.jianshu.com/p/0b78f1156642
 * aop目标测试类
 * @author linzhenghui
 * @date 2020/7/12
 */
@Aspect
@Component
public class TestAspect {

    /**
     * 获取切点
     */
    @Pointcut(value = "@annotation(com.delicacy.cookies.aop.TargetAnnotation)")
    public void testPoint() {
    }

    /**
     * 处理方法前
     * @param joinPoint 方法连接点
     * @param targetAnnotation   切点注解
     */
    //@Before(value = "testPoint() && @annotation(targetAnnotation)")
    public String before(JoinPoint joinPoint, TargetAnnotation targetAnnotation) {
        System.out.println("before");
        debugMessage(joinPoint, targetAnnotation, null);
        return "before";
    }

    /**
     * 处理方法后（返回值无效）
     * @param joinPoint 方法连接点
     * @param targetAnnotation   切点注解
     */
    // @After(value = "testPoint() && @annotation(targetAnnotation)")
    public String after(JoinPoint joinPoint, TargetAnnotation targetAnnotation) {
        System.out.println("after");
        debugMessage(joinPoint, targetAnnotation, null);
        return "after";
    }

    /**
     * 能获取返回值
     * 处理方法返回后
     * @param joinPoint 方法连接点
     * @param targetAnnotation   切点注解
     */
    //@AfterReturning(value = "testPoint() && @annotation(targetAnnotation)", returning = "ret")
    public String afterReturning(JoinPoint joinPoint, TargetAnnotation targetAnnotation, Object ret) {
        System.out.println("afterReturning");
        debugMessage(joinPoint, targetAnnotation, null);
        return "afterReturning";
    }

    /**
     * 环绕方法
     * @param joinPoint 方法连接点
     * @param targetAnnotation   切点注解
     */
    //@Around(value = "testPoint() && @annotation(targetAnnotation)")
    public void around(ProceedingJoinPoint joinPoint, TargetAnnotation targetAnnotation) throws Throwable {
        System.out.println("around");
        Object proceed = joinPoint.proceed();
        debugMessage(joinPoint, targetAnnotation, null);
    }

    /**
     * 执行完异常捕获方法后，抛出异常
     * 发生异常
     * @param joinPoint 切点
     * @param targetAnnotation 咋切入
     * @param throwable 异常
     * @return hello
     */
    //@AfterThrowing(pointcut = "testPoint() && @annotation(targetAnnotation)", throwing = "throwable")
    public String afterThrowing(JoinPoint joinPoint, TargetAnnotation targetAnnotation, Throwable throwable) {
        System.out.println("afterThrowing");
        debugMessage(joinPoint, targetAnnotation, throwable);
        return "hello";
    }

    private void debugMessage(JoinPoint joinPoint, TargetAnnotation targetAnnotation, Throwable throwable){
        // 切点情况
        Object[] args = joinPoint.getArgs();
        String kind = joinPoint.getKind();

        Signature signature = joinPoint.getSignature();
        Class declaringType = signature.getDeclaringType();
        String declaringTypeName = signature.getDeclaringTypeName();
        int modifiers = signature.getModifiers();
        String name = signature.getName();

        SourceLocation sourceLocation = joinPoint.getSourceLocation();

        JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();
        Signature signature1 = staticPart.getSignature();
        SourceLocation sourceLocation1 = staticPart.getSourceLocation();
        System.out.println(signature1 == signature);
        System.out.println(sourceLocation1 == sourceLocation);

        // 注解情况
        boolean value = targetAnnotation.value();
        // ending
        System.out.println("ending...");
    }

}
