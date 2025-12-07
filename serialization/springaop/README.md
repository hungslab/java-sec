# SpringAOP

gataget


```
JdkDynamicAopProxy.invoke()->
ReflectiveMethodInvocation.proceed()->
AspectJAroundAdvice->invoke->
org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod()->
method.invoke()
```