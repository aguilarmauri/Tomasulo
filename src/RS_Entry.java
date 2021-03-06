import java.util.concurrent.Semaphore;

//Estacion de Reserva

public class RS_Entry {
	
	private int dest;
	private boolean busy;
	private String op;
	private int vj,vk;
	private String qj,qk;
	private int clock;
	private Semaphore sem;
	private boolean ready;
	
	public RS_Entry() {
		dest = -1; 
		busy = false;
		op = "";
		vj = 0; //ver
		vk = 0; //ver
		qj = ""; //ver
		qk = ""; //ver
		clock = 0;
		sem = new Semaphore(1);
	}
	
	public void acquire() throws InterruptedException {
		sem.acquire();
	}
	
	public void release() {
		sem.release();
	}
	
	public boolean getBusy() {
		return busy;
	}	
	
	public String getOp() {
		return op;
	}
	
	public int getVj() {
		return vj;
	}
	
	public int getVk() {
		return vk;
	}
	
	public void setDest(int dest) {
		this.dest = dest;
	}
	
	public void setBusy(boolean busy) {
		this.busy = busy;
	}
	
	public void setOp(String op) {
		this.op = op;
	}
	
	public void setVj(int vj) {
		this.vj = vj;
	}
	
	public void setVk(int vk) {
		this.vk = vk;
	}
	
	public void setQj(String qj) {
		this.qj = qj;
	}
	
	public void setQk(String qk) {
		this.qk = qk;
	}

	public String getQj() {
		return qj;
	}

	public String getQk() {
		return qk;
	}

	public int getDest() {
		return dest;
	}
	
	public void setClock(int value) {
		clock = value;
	}
	
	public int getClock() {
		return clock;
	}

	public void setReady(boolean b) {
		ready = b;
	}

	public boolean getReady() {
		return ready;
	}

}
