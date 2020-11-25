package com.github.elhamjamshidpey.africanBoardGame.view.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.elhamjamshidpey.africanBoardGame.Game;
import com.github.elhamjamshidpey.africanBoardGame.component.Board;
import com.github.elhamjamshidpey.africanBoardGame.exception.InvalidMoveException;
import com.github.elhamjamshidpey.africanBoardGame.view.BoardView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path = "game")
public class VaadinBoardView extends UI implements ViewDisplay,BoardView{

	@Autowired
	private Game game;

	private Panel springViewDisplay;
	private VerticalLayout boardLayout = new VerticalLayout();
	private final VerticalLayout mainLayout = new VerticalLayout();

	@Override
	public void showView(View view) {
		springViewDisplay.setContent((Component) view);

	}

	@Override
	protected void init(VaadinRequest request) {
		setContent(mainLayout);

		if (game.getCurrentPlayer() == null) {
			Link loginLink = new Link("Click here for login", new ExternalResource("login"));
			mainLayout.addComponent(loginLink);

		} else {
			showBoard();

			TextField srcIndex = new TextField("Enter your pit number for remove stone(s):");


			mainLayout.addComponent(srcIndex);

			Button playButton = new Button("PLAY");
			Button reloadGameButton = new Button("RELOAD");

			playButton.addClickListener(new Button.ClickListener() {
				public void buttonClick(ClickEvent event) {
					try {
						game.play(Integer.valueOf(srcIndex.getValue()));
						showBoard();
						if (game.gameIsFinish()) {
							Label winnerName = new Label();
							winnerName.setCaption("Game Is Finish , Winner " + game.findWinner());
							mainLayout.removeComponent(playButton);
							mainLayout.addComponents(winnerName);
						}
					} catch (InvalidMoveException e) {
						Notification.show("Moving not allowed!", e.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
					}

				}
			});
			HorizontalLayout playButtons = new HorizontalLayout();
			playButtons.addComponent(playButton);
			playButtons.addComponent(reloadGameButton);
			mainLayout.addComponent(playButtons);

			reloadGameButton.addClickListener(new Button.ClickListener() {
				public void buttonClick(ClickEvent event) {
					game.reload();
					getPage().setLocation("login");
				}
			});
		}

	}
	
	@Override
	public void showBoard() {
		boardLayout.removeAllComponents();
		Board board = game.getBoard();
		boardLayout.addComponent(new Label("Current Player: " + game.getCurrentPlayer().getName()));
		boardLayout.addComponent(new Label(game.getFirstPlayer().getName() + " pits:"));
		HorizontalLayout firstPlayerRow = new HorizontalLayout();
		firstPlayerRow.addComponent(new Button("L: " + board.getFirstPlayerLargerPit().getStoneNumber()));
		boardLayout.addComponent(firstPlayerRow);
		board.getFirstPlayerAPits()
				.forEach(p -> firstPlayerRow.addComponent(new Button(p.getStoneNumber().toString())));

		boardLayout.addComponent(new Label(game.getSecondPlayer().getName() + " pits:"));
		HorizontalLayout secondPlayerRow = new HorizontalLayout();
		board.getSecondPlayerBPits()
				.forEach(p -> secondPlayerRow.addComponent(new Button(p.getStoneNumber().toString())));
		secondPlayerRow.addComponent(new Button("L: " + board.getSecondPlayerLargerPit().getStoneNumber()));
		boardLayout.addComponent(secondPlayerRow);
		mainLayout.replaceComponent(boardLayout, boardLayout);
	}

}
