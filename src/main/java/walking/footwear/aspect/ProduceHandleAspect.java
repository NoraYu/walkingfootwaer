package walking.footwear.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Component
@Aspect
public class ProduceHandleAspect {
    private Logger logger=Logger.getLogger(getClass().getName());
    @Pointcut("execution(* walking.footwear.service.*.*(..))")
    private void anyService(){}

    @Pointcut("execution(* walking.footwear.service.*.newitem(..))")
    private void newItem(){}

    @Pointcut("execution(* walking.footwear.service.*.all(..))")
    private void findAllItems(){}

    @Before("anyService()")
    public void before(JoinPoint joinPoint){
        String method=joinPoint.getSignature().toLongString();
        logger.info("=====>>Arriving Service Layer. Going to run "+ method);
        Object[] args=joinPoint.getArgs();
        Arrays.stream(args).forEach(a->logger.info(" argument: "+a.toString()));
    }

    @AfterReturning(pointcut = "anyService()",returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result){
        String method=joinPoint.getSignature().toLongString();
        logger.info("=====>>Finish running "+ method);
        logger.info("result: "+ result);

    }

    @Around("newItem() || findAllItems() ")
    public Object aroundCreatNewItem(ProceedingJoinPoint pjp) throws  Throwable{
        String method=pjp.getSignature().toString();
        logger.info("\n=====>>> Executing @Around on method: " + method);
        Object result =pjp.proceed();
        logger.info(method+" finished. Result :"+ result);
        return result;

    }

}
