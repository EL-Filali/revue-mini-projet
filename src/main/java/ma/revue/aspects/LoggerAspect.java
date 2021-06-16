package ma.revue.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.event.Level;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggerAspect {


    static Logger logger = Logger.getLogger("visitor");


    @AfterReturning(pointcut="execution(* ma.revue.controllers.VisitorController.*(..))",returning = "result")
    public void saveVisitorLog(JoinPoint join, ResponseEntity<?> result){
        if((result.getStatusCodeValue()==200)||(result.getStatusCodeValue()==201)){
            logger.info("[VISITOR] ["+join.getSignature()+"]  succes");
        }else{
            logger.info("visitor "+join.getSignature()+" Failed");
        }

    }
    @AfterReturning(pointcut="execution(* ma.revue.controllers.UserController.*(..))",returning = "result")
    void saveUserLog(JoinPoint join, ResponseEntity<?> result){
        if((result.getStatusCodeValue()==200)||(result.getStatusCodeValue()==201)){
            logger.info("user "+join.getSignature()+" succes");
        }else{
            logger.info("user "+join.getSignature()+" Failed");
        }

    }

    @AfterReturning(pointcut="execution(* ma.revue.controllers.ComiteController.*(..))",returning = "result")
    void saveComiteLog(JoinPoint join, ResponseEntity<?> result){
        if((result.getStatusCodeValue()==200)||(result.getStatusCodeValue()==201)){
            logger.info("Comite "+join.getSignature()+" succes");
        }else{
            logger.info("Comite "+join.getSignature()+" Failed");
        }

    }
    @AfterReturning(pointcut="execution(* ma.revue.controllers.RefreeController.*(..))",returning = "result")
    void saveRefreeLog(JoinPoint join, ResponseEntity<?> result){
        if((result.getStatusCodeValue()==200)||(result.getStatusCodeValue()==201)){
            logger.info("Refree "+join.getSignature()+" succes");
        }else{
            logger.info("Refree "+join.getSignature()+" Failed");
        }

    }



}
