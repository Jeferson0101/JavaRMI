package multiplicacao.matriz.server.rmi.model;

public class MultiplicacaoMatrizResult {
	public long[] mResult;
	public int mLinhaCalculada;
	
	public MultiplicacaoMatrizResult(int aLinhaCalculada) {
		super();
		this.mLinhaCalculada = aLinhaCalculada;
	}
	
	public MultiplicacaoMatrizResult() {
		super();
	}
}
