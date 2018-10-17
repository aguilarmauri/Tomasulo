import java.util.concurrent.Semaphore;

public class Bus {
	
	//private Integer data_bus = new Integer(0);
	private int data;
	private String tag; 
	private Semaphore sem_write;
	private Semaphore sem_read_add;
	private Semaphore sem_read_mul;
	private Semaphore sem_read_rob;
	private int counter;
	private int reads;
	private Semaphore sem_del;
	private Semaphore sem_read;

	public Bus() {
		sem_write = new Semaphore(1);
		sem_read_add = new Semaphore(1);
		sem_read_mul = new Semaphore(1);
		sem_read_rob = new Semaphore(1);
		try {
			sem_read_add.acquire();
			sem_read_mul.acquire();
			sem_read_rob.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		counter = 0;
		data = -1;
		tag = "null";
		write_acquire();
		reads = 0;
		sem_del = new Semaphore(3);
		acquireDelete(3);
		sem_read = new Semaphore(3);
		try {
			sem_read.acquire(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void write_acquire() {
		try {
			sem_write.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean write_tryAcquire() {
		boolean result = sem_write.tryAcquire();
		return result;
	}
	
	public void write_release() {
		//delete();
		if( !haveAvailables() )
			sem_write.release();
	}

	public void delete() {
		data = -1;
		tag = "null";
	}

	public void read_acquire(String unit) throws InterruptedException {
		switch (unit) {
			case "A":
				sem_read_add.acquire();
				break;
			case "M":
				sem_read_mul.acquire();
				break;
			case "R":
				sem_read_rob.acquire();
				break;
		}
	}
	
	public void write_ready() {
		/*counter++;
		System.out.println("COUNTER++ "+counter);
		if(counter==3) {
			sem_read_add.release();
			sem_read_mul.release();
			sem_read_rob.release();
			counter=0;
		}*/
		
		//sem_read.release();
		//System.out.println("sem_read.availablePermits()++ "+sem_read.availablePermits());
		/*if(sem_read.availablePermits() == 3) {
			try {
				sem_read.acquire(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sem_read_add.release();
			sem_read_mul.release();
			sem_read_rob.release();
			
		}*/
		
		sem_read.release();		
		if(sem_read.tryAcquire(3)) {
			sem_read_add.release();
			sem_read_mul.release();
			sem_read_rob.release();
		}
			
		
	}	
	
	public void set(int data, String tag) {
		this.data = data;
		this.tag = tag;
	}

	public int getData() {
		return data;
	}
	
	public String getTag() {
		return tag;
	}

	public boolean haveAvailables() {
		if(sem_write.availablePermits() > 0) return true;
		else return false;
	}

	public void tryDeleteCDB() {
		/*reads++;
		System.out.println("reads++");
		if(reads == 3) {
			delete();
			reads = 0;
			System.out.println("CDB borrado");
		}*/
		System.out.println("sem_del.release(1);");
		sem_del.release(1);
	}

	public void acquireDelete(int i) {
		System.out.println("acquireDelete PERMITS "+sem_del.availablePermits());
		try {
			sem_del.acquire(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
