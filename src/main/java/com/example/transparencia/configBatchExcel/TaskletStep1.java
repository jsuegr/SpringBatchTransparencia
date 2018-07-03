package com.example.transparencia.configBatchExcel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.example.transparencia.model.ReporteFormato;

@Component("taskletStep1")
public class TaskletStep1 implements Tasklet{
 
	private static final Log log = LogFactory.getLog(TaskletStep1.class);
    
    @Override
    public RepeatStatus execute(StepContribution contribution,
            ChunkContext chunkContext) throws Exception {
    	
    	log.info("------------------------------------------");
		log.info("Inside step 1");
 
		ReporteFormato reporteFormato = new ReporteFormato();
		chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("reporteFormato", reporteFormato);
		
		log.info("------------------------------------------");

        return RepeatStatus.FINISHED;
    }
    
     
}