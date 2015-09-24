package org.socraticgrid.hl7.services.uc.internal.idgenerators;

import org.socraticgrid.hl7.services.uc.internal.interfaces.IdGeneratorIntf;

/**
 * Simple Millisecond Id generator - Suitable only for single threaded testing and Proof of concept work.
 * Should never be used in a production environment
 * @author Jerry Goodnough
 *
 */
public class TimeBasedIdGenerator implements IdGeneratorIntf
{
	private static long id;
	
	@Override
	public synchronized String getNewId()
	{	
		/**
		 * Linux and Mac have resolutions > 1ms ... Windows is 1000/64
		 * so it is possible to return identical ids if not compared.
		 * Run the test and see how many red numbers you find!
		 */
		while(true){
			if(System.currentTimeMillis()!=id){
				id=System.currentTimeMillis();
				return id+"";
			}
		}
	}

	
	public static void main(String...strings){
		long id = 0;
		//test w/o comparison
		System.out.println("****** Running test w/o comparison.");
		long start = System.nanoTime();
		for(int i=0; i<100; i++){
			if(System.currentTimeMillis()!=id){
				id=System.currentTimeMillis();
				System.out.println("\t"+id);
			}
			else {
				System.err.println("\t"+id);
			}
		}
		long woelapTime = System.nanoTime()-start;
		System.out.println("****** w/o comparison completed in "+woelapTime+" nano seconds!");

		
		//test w/ comparison
		System.out.println("****** Running test w/ comparison.");
		start = System.nanoTime();
		for(int i=0; i<100; i++){
			while(true){
				if(System.currentTimeMillis()!=id){
					id=System.currentTimeMillis();
					System.out.println("\t"+id);
					break;
				}
			}
		}
		long welapTime = System.nanoTime()-start;
		System.out.println("****** w/ comparison completed in "+(welapTime)+" nano seconds! \n\t"+(welapTime-woelapTime)+
				" nanos more ("+(welapTime-woelapTime)/1000000.0+" millis more) than w/o comparison BUT no duplicates.");
	}
}
