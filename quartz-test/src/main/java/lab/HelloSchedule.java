package lab;

import java.util.Date;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;

public class HelloSchedule {
	public HelloSchedule() throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		sched.start();
		JobDetail jd = new JobDetailImpl("myjob", sched.DEFAULT_GROUP,
				HelloJob.class);
		SimpleTrigger st = new SimpleTriggerImpl("mytrigger", sched.DEFAULT_GROUP,
				new Date(), null, SimpleTrigger.REPEAT_INDEFINITELY,
				60L * 1000L);
		sched.scheduleJob(jd, st);
	}

	public static void main(String args[]) {
		try {
			new HelloSchedule();
		} catch (Exception e) {
		}
	}
}