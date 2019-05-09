package cpumonitor;

import java.util.List;

import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.sensors.Temperature;

public class CpuMonitor {
	//间隔时间
	//温度
	//bat地址
	public static void main(String[] args) throws Exception {
		 while(true) {
       	  Thread.sleep(Integer.valueOf(args[0]));
		  Components components = JSensors.get.components();
		  //水温监控程序，当你的水泵坏了，然后你又挂机出去玩了，
		  //然后你的硬管到70度的时候开始变软，导致漏液，需要自动关机
          List<Cpu> cpus = components.cpus;
  		    if (cpus != null) {
  		        for (final Cpu cpu : cpus) {
  		            //System.out.println("Found CPU component: " + cpu.name);
  		            if (cpu.sensors != null) {
  		              //Print temperatures
  		              List<Temperature> temps = cpu.sensors.temperatures;
  		              for (final Temperature temp : temps) {
  		            	  if("Temp CPU Package".equals(temp.name)) {
  		            		 if(temp.value>=Integer.valueOf(args[1]) ) {
  		            			 //System.out.println("报警");
  		            			 //调用关机命令
  		            			Runtime.getRuntime().exec(args[2]);
  		            		 }/*else {
  		            			 System.out.println("正常");
  		            		 }*/
  		            	  }
  		              }
  		  
  		              //Print fan speed
  		              /*List<Fan> fans = cpu.sensors.fans;
  		              for (final Fan fan : fans) {
  		                  System.out.println(fan.name + ": " + fan.value + " RPM");
  		              }*/
  		            }
  		        }
  		    }
        	  
          }
		    

	}

}
