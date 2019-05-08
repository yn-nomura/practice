package java_s04;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Expenses;
import dao.ExpensesDAO;

	/**
	 *経費一覧の実装
	 **/

	@Path("expenses")
	public class ExpensesResource{
		private final ExpensesDAO dao =new ExpensesDAO();

		/**
		 * 一覧用に部署情報を一部取得する
		 * @return 部署情報のリストをJSON形式で返す
		 */
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Expenses>findPart(){
			return dao.findPart();
		}

		/**
		 * 指定した経費情報を登録*/
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Expenses create(Expenses expenses) throws WebApplicationException{
			validate(expenses);
			return dao.create(expenses);
		}

		/**
		 * 指定したIDの経費情報を削除する*/
		@DELETE
		@Path("{id}")
		public void remove(@PathParam("id")int id){
			dao.remove(id);
		}

		/**
		 * 入力内容のチェックを行う。
		 * @param post入力データを保持したモデル
		 * @throws ValidationException 入力データチェックに失敗した場合に送出される。
		 */
		private void validate(Expenses expenses)throws WebApplicationException{
			if (expenses.getName().isEmpty()){
				throw new WebApplicationException(Response.Status.BAD_REQUEST);
			}
		}
}
