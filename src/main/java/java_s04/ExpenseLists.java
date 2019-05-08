package java_s04;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Post;
import dao.ExpensesDAO;

/**
 *経費一覧の実装
 **/

@Path("expenses")
public class ExpensesResource{
	private final ExpensesDAO dao =new ExpensesDAO();

	/**
	 * 一覧用に部署情報を全件取得する
	 * @return 部署情報のリストをJSON形式で返す
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Expenses>findall(){
		return dao.findAll();
	}

	/**
	 * 入力内容のチェックを行う。
	 * @param post　入力データを保持したモデル
	 * @throws ValidationException 入力データチェックに失敗した場合に送出される。
	 */
	private void validate(Post post)throws WebApplicationException{
		if (post.getName().isEmpty()){
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
	}
}