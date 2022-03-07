package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 日報データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_REP)

@NamedQueries({
	@NamedQuery(
		name = JpaConst.Q_REP_GET_ALL,
		query = JpaConst.Q_REP_GET_ALL_DEF),

	@NamedQuery(
		name = JpaConst.Q_REP_COUNT,
		query = JpaConst.Q_REP_COUNT_DEF),

	@NamedQuery(
		name = JpaConst.Q_REP_GET_ALL_MINE,
		query = JpaConst.Q_REP_GET_ALL_MINE_DEF),

	@NamedQuery(
		name = JpaConst.Q_REP_COUNT_ALL_MINE,
		query = JpaConst.Q_REP_COUNT_ALL_MINE_DEF)
})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity

public class Report {

	/**
	 * id
	 */
	@Id
	@Column(name = JpaConst.REP_COL_ID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 日報を登録した従業員
	 *
	 * [Report（日報）とEmployee（従業員）の関係]
	 * ひとりの従業員は毎日日報を作成するので複数の日報を持っていますが、1枚の日報から見たら作成者はたった1人だと言えます。Report と Employee のような関係は 1対多 と呼んでいます。なお、互いに複数のデータを持つことが考えられる場合の 多対多 という関係もあります。
	 *
	 */
	@ManyToOne
	@JoinColumn(name = JpaConst.REP_COL_EMP, nullable = false)
	private Employee employee;

	/**
	 * いつの日報かを示す日付
	 */
	@Column(name = JpaConst.REP_COL_REP_DATE, nullable = false)
	private LocalDate reportDate;

	/**
	 * 日報のタイトル
	 */
	@Column(name = JpaConst.REP_COL_TITLE, length = 255, nullable = false)
	private String title;

	/**
	 * 日報の内容
	 *
	 * [@Lobアノテーション]
	 * content は日報の詳細な内容を記述するフィールドです。フォームの部品はテキストエリア（<textarea>）にして改行の情報を保持したいのですが、とくに何もしなければテキストボックス（1行の文字列）の扱いと同じになります。テキストエリアの指定を行うのが @Lob アノテーションです。これを指定することで、改行もデータベースに保存されます。
	 *
	 */
	@Lob
	@Column(name = JpaConst.REP_COL_CONTENT, nullable = false)
	private String content;

	/**
	 * 登録日時
	 */
	@Column(name = JpaConst.REP_COL_CREATED_AT, nullable = false)
	private LocalDateTime createdAt;

	/**
	 * 更新日時
	 */
	@Column(name = JpaConst.REP_COL_UPDATED_AT, nullable = false)
	private LocalDateTime  updatedAt;
}
