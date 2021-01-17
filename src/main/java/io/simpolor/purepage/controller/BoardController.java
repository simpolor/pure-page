package io.simpolor.purepage.controller;

import io.simpolor.purepage.repository.entity.Board;
import io.simpolor.purepage.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(value="/list")
	public ModelAndView list() {

		long totalCount = boardService.getTotalCount();
		List<Board> students = boardService.getAll();

		ModelAndView mav = new ModelAndView();
		mav.addObject("totalCount", totalCount);
		mav.addObject("list", students);
		mav.setViewName("board_list");

		return mav;
	}

	@RequestMapping(value="/view/{id}", method=RequestMethod.GET)
	public ModelAndView view(@PathVariable long id) {

		Board result = boardService.get(id);

		ModelAndView mav = new ModelAndView();
		mav.addObject("board", result);
		mav.setViewName("board_view");

		return mav;
	}

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView postForm() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("board_register");
		return mav;
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView post(Board board) {

		Board result = boardService.register(board);

		ModelAndView mav = new ModelAndView();
		mav.addObject("board", result);
		mav.setViewName("redirect:/board/view/"+result.getId());
		return mav;
	}

	@RequestMapping(value="/modify/{id}", method=RequestMethod.GET)
	public ModelAndView putForm(@PathVariable long id) {

		Board board = boardService.get(id);

		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.setViewName("board_modify");
		return mav;
	}

	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public ModelAndView modify(Board board) {

		Board result = boardService.modify(board);

		ModelAndView mav = new ModelAndView();
		mav.addObject("board", result);
		mav.setViewName("redirect:/board/view/"+result.getId());
		return mav;
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public ModelAndView delete(@PathVariable long id) {

		boardService.delete(id);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/board/list");
		return mav;
	}

}
