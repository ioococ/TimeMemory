package personal.shengyu.memory.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import personal.shengyu.memory.config.PoetryResult;
import personal.shengyu.memory.domain.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import personal.shengyu.memory.domain.vo.BaseRequestVO;
import personal.shengyu.memory.domain.vo.CommentVO;


/**
 * <p>
 * 文章评论表 服务类
 * </p>
 *
 * @author sara
 * @since 2021-08-13
 */
public interface CommentService extends IService<Comment> {

    PoetryResult saveComment(CommentVO commentVO);

    PoetryResult deleteComment(Integer id);

    PoetryResult<BaseRequestVO> listComment(BaseRequestVO baseRequestVO);

    PoetryResult<Page> listAdminComment(BaseRequestVO baseRequestVO, Boolean isBoss);
}
