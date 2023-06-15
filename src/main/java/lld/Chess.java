package lld;

import java.util.List;

enum GameStatus {
    ACTIVE, BLACK_WIN, WHITE_WIN, FORFEIT, STALEMATE, RESIGNATION
}

class Person {
    private String name;
    private String email;
    private String phone;
}

class ChessPlayer {
    private Person person;
    private boolean whiteSide = false;

    public ChessPlayer(Person person, boolean whiteSide){
        this.person = person;
        this.whiteSide = whiteSide;
    }

    public boolean isWhiteSide() {
        return this.whiteSide == true;
    }
}

class Box {
    private Piece piece;
    private int x;
    private int y;

    public Box(Piece piece, int x, int y) {
        this.setPiece(piece);
        this.setX(x);
        this.setY(y);
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece p) {
        this.piece = p;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

abstract class Piece {

    private boolean killed = false;
    private boolean white = false;

    public Piece(boolean white) {
        this.setWhite(white);
    }

    public boolean isWhite() {
        return this.white == true;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public boolean isKilled() {
        return this.killed == true;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public abstract boolean canMove(Board board, Box start, Box end);
}

class King extends Piece {
    private boolean castlingDone = false;

    public King(boolean white) {
        super(white);
    }

    public boolean isCastlingDone() {
        return this.castlingDone == true;
    }

    public void setCastlingDone(boolean castlingDone) {
        this.castlingDone = castlingDone;
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        // we can't move the piece to a box that has a piece of the same color
        if(end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        if(x + y == 1) {
            // check if this move will not result in king being attacked, if so return true
            return true;
        }

        return this.isValidCastling(board, start, end);
    }

    private boolean isValidCastling(Board board, Box start, Box end) {

        if(this.isCastlingDone()) {
            return false;
        }

        // check for the white king castling
        if(this.isWhite()
                && start.getX() == 0 && start.getY() == 4 && end.getY() == 0) {
            // confirm if white king moved to the correct ending box
            if (Math.abs(end.getY() - start.getY()) == 2) {
                // check if there the Rook is in the correct position
                // check if there is no piece between Rook and the King
                // check if the King or the Rook has not moved before
                // check if this move will not result in king being attacked
                //...
                this.setCastlingDone(true);
                return true;
            }
        } else {
            // check for the black king castling
            this.setCastlingDone(true);
            return true;
        }

        return false;
    }

    public boolean isCastlingMove(Box start, Box end) {
        // check if the starting and ending position are correct
        return false;
    }
}

class Knight extends Piece {
    public Knight(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {

        // we can't move the piece to a box that has a piece of the same color
        if(end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return x * y == 2;
    }
}

class Pawn extends Piece {
    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {

        // we can't move the piece to a box that has a piece of the same color
        if(end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return x * y == 2;
    }
}

class Rook extends Piece {
    public Rook(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {

        // we can't move the piece to a box that has a piece of the same color
        if(end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return x * y == 2;
    }
}

class Bishop extends Piece {
    public Bishop(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {

        // we can't move the piece to a box that has a piece of the same color
        if(end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return x * y == 2;
    }
}

class Board {
    Box[][] boxes;

    public Board() {
        this.initializeBoard();
    }

    public Box getBox(int x, int y) throws Exception {

        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new Exception("Index out of bound");
        }

        return boxes[x][y];
    }

    public void initializeBoard() {
        // initialize white pieces
        boxes[0][0] = new Box(new Rook(true), 0, 0);
        boxes[0][1] = new Box(new Knight(true), 0, 1);
        boxes[0][2] = new Box(new Bishop(true), 0, 2);
        //...
        boxes[1][0] = new Box(new Pawn(true), 1, 0);
        boxes[1][1] = new Box(new Pawn(true), 1, 1);
        //...

        // initialize black pieces
        boxes[7][0] = new Box(new Rook(false), 7, 0);
        boxes[7][1] = new Box(new Knight(false), 7, 1);
        boxes[7][2] = new Box(new Bishop(false), 7, 2);
        //...
        boxes[6][0] = new Box(new Pawn(false), 6, 0);
        boxes[6][1] = new Box(new Pawn(false), 6, 1);
        //...

        // initialize remaining boxes without any piece
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                boxes[i][j] = new Box(null, i, j);
            }
        }
    }
}

class Move {
    private ChessPlayer chessPlayer;
    private Box start;
    private Box end;
    private Piece pieceMoved;
    private Piece pieceKilled;
    private boolean castlingMove = false;

    public Move(ChessPlayer chessPlayer, Box start, Box end){
        this.chessPlayer = chessPlayer;
        this.start = start;
        this.end = end;
        this.pieceMoved = start.getPiece();
    }

    public boolean isCastlingMove() {
        return this.castlingMove == true;
    }

    public void setCastlingMove(boolean castlingMove) {
        this.castlingMove = castlingMove;
    }

    public Box getStart(){
        return start;
    }

    public Box getEnd(){
        return end;
    }

    public ChessPlayer getChessPlayer(){
        return chessPlayer;
    }

    public void setPieceKilled(Piece destPiece) {
            pieceKilled = destPiece;
    }
}

public class Chess {

    private ChessPlayer[] chessPlayers;
    private Board board;
    private ChessPlayer currentTurn;
    private GameStatus status;
    private List<Move> movesPlayed;

    private void initialize(ChessPlayer p1, ChessPlayer p2) {

        chessPlayers[0] = p1;
        chessPlayers[1] = p2;

        board.initializeBoard();

        if(p1.isWhiteSide()) {
            this.currentTurn = p1;
        } else {
            this.currentTurn = p2;
        }

        movesPlayed.clear();
    }

    public boolean isEnd() {
        return this.getStatus() != GameStatus.ACTIVE;
    }

    public GameStatus getStatus() {
        return this.status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public boolean playerMove(ChessPlayer chessPlayer, int startX, int startY, int endX, int endY) throws Exception {
        Box startBox = board.getBox(startX, startY);
        Box endBox = board.getBox(startY, endY);
        Move move = new Move(chessPlayer, startBox, endBox);
        return this.makeMove(move);
    }

    private boolean makeMove(Move move) {

        Piece sourcePiece = move.getStart().getPiece();
        ChessPlayer chessPlayer = move.getChessPlayer();

        if (sourcePiece == null) {
            return false;
        }

        // valid player
        if (chessPlayer != currentTurn) {
            return false;
        }

        if (sourcePiece.isWhite() != chessPlayer.isWhiteSide()) {
            return false;
        }

        // valid move?
        if (!sourcePiece.canMove(board, move.getStart(), move.getEnd())){
            return false;
        }

        // kill?
        Piece destPiece = move.getStart().getPiece();
        if (destPiece != null) {
            destPiece.setKilled(true);
            move.setPieceKilled(destPiece);
        }

        // castling?
        /*if (sourcePiece != null && sourcePiece instanceof King
                && ((King)sourcePiece).isCastlingMove()) {
            move.setCastlingMove(true);
        }*/

        // store the move
        movesPlayed.add(move);

        // move piece from the stat box to end box
        move.getEnd().setPiece(move.getStart().getPiece());
        move.getStart().setPiece(null);

        if (destPiece != null && destPiece instanceof King) {
            if(chessPlayer.isWhiteSide()) {
                this.setStatus(GameStatus.WHITE_WIN);
            } else {
                this.setStatus(GameStatus.BLACK_WIN);
            }
        }

        // set the current turn to the other player
        if(this.currentTurn == chessPlayers[0]) {
            this.currentTurn = chessPlayers[1];
        } else {
            this.currentTurn = chessPlayers[0];
        }

        return true;
    }
}

