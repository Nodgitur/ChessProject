import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/*
	This class can be used as a starting point for creating your Chess game project. The only piece that 
	has been coded is a white pawn...a lot done, more to do!
*/

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
    int startX;
    int startY;
    int initialX;
    int initialY;
    JPanel panels;
    JLabel pieces;


    public ChessProject() {
        Dimension boardSize = new Dimension(600, 600);

        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground(i % 2 == 0 ? Color.white : Color.gray);
            else
                square.setBackground(i % 2 == 0 ? Color.gray : Color.white);
        }

        // Setting up the Initial Chess board.
        for (int i = 8; i < 16; i++) {
            pieces = new JLabel(new ImageIcon("WhitePawn.png"));
            panels = (JPanel) chessBoard.getComponent(i);
            panels.add(pieces);
        }
        pieces = new JLabel(new ImageIcon("WhiteRook.png"));
        panels = (JPanel) chessBoard.getComponent(0);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
        panels = (JPanel) chessBoard.getComponent(1);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
        panels = (JPanel) chessBoard.getComponent(6);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteBishop.png"));
        panels = (JPanel) chessBoard.getComponent(2);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteBishop.png"));
        panels = (JPanel) chessBoard.getComponent(5);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKing.png"));
        panels = (JPanel) chessBoard.getComponent(3);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
        panels = (JPanel) chessBoard.getComponent(4);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteRook.png"));
        panels = (JPanel) chessBoard.getComponent(7);
        panels.add(pieces);
        for (int i = 48; i < 56; i++) {
            pieces = new JLabel(new ImageIcon("BlackPawn.png"));
            panels = (JPanel) chessBoard.getComponent(i);
            panels.add(pieces);
        }
        pieces = new JLabel(new ImageIcon("BlackRook.png"));
        panels = (JPanel) chessBoard.getComponent(56);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKnight.png"));
        panels = (JPanel) chessBoard.getComponent(57);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKnight.png"));
        panels = (JPanel) chessBoard.getComponent(62);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackBishop.png"));
        panels = (JPanel) chessBoard.getComponent(58);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackBishop.png"));
        panels = (JPanel) chessBoard.getComponent(61);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKing.png"));
        panels = (JPanel) chessBoard.getComponent(59);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackQueen.png"));
        panels = (JPanel) chessBoard.getComponent(60);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackRook.png"));
        panels = (JPanel) chessBoard.getComponent(63);
        panels.add(pieces);
    }

    /*
        Main method that gets the ball moving.
    */
    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /*
        This method checks if there is a piece present on a particular square.
    */
    private Boolean piecePresent(int x, int y) {
        Component c = chessBoard.findComponentAt(x, y);
        return !(c instanceof JPanel);
    }

    /*
        This is a method to check if a piece is a Black piece.
    */
    private Boolean checkWhiteOpponent(int newX, int newY) {
        Boolean opponent;
        Component c1 = chessBoard.findComponentAt(newX, newY);
        JLabel awaitingPiece = (JLabel) c1;
        String tmp1 = awaitingPiece.getIcon().toString();
        opponent = tmp1.contains("Black");
        return opponent;
    }

    /*
        Checks if a piece is a white piece
     */
    private Boolean checkBlackOpponent(int newX, int newY) {
        Boolean opponent;
        Component c1 = chessBoard.findComponentAt(newX, newY);
        JLabel awaitingPiece = (JLabel) c1;
        String tmp1 = awaitingPiece.getIcon().toString();
        opponent = tmp1.contains("White");
        return opponent;
    }

    /*
        This method is called when we press the Mouse. So we need to find out what piece we have
        selected. We may also not have selected a piece!
    */
    public void mousePressed(MouseEvent e) {
        chessPiece = null;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel)
            return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel) c;
        initialX = e.getX();
        initialY = e.getY();
        startX = (e.getX() / 75);
        startY = (e.getY() / 75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    /*
       This method is used when the Mouse is released...we need to make sure the move was valid before
       putting the piece back on the board.
   */
    public void mouseReleased(MouseEvent e) {
        if (chessPiece == null) return;

        chessPiece.setVisible(false);
        Boolean success = false;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        String tmp = chessPiece.getIcon().toString();
        String pieceName = tmp.substring(0, (tmp.length() - 4));
        Boolean validMove = false;

        int xLanding = (e.getX() / 75);
        int yLanding = (e.getY() / 75);
        int xMovement = Math.abs((e.getX() / 75) - startX);
        int yMovement = Math.abs((e.getY() / 75) - startY);


		/*
			The only piece that has been enabled to move is a White Pawn...but we should really have this is a separate
			method somewhere...how would this work.

			So a Pawn is able to move two squares forward one its first go but only one square after that.
			The Pawn is the only piece that cannot move backwards in chess...so be careful when committing
			a pawn forward. A Pawn is able to take any of the opponent’s pieces but they have to be one
			square forward and one square over, i.e. in a diagonal direction from the Pawns original position.
			If a Pawn makes it to the top of the other side, the Pawn can turn into any other piece, for
			demonstration purposes the Pawn here turns into a Queen.
		*/

        System.out.println("----------------------------------------");
        System.out.println("The starting coordinates are: " + startX + " , " + startY);
        System.out.println("The x-axis movement is: " + " " + xMovement);
        System.out.println("The y-axis movement is: " + " " + yMovement);
        System.out.println("The landing coordinates are: " + " " + xLanding + " , " + yLanding);
        System.out.println("----------------------------------------");

        /*
            Black Pieces below
         */

        //Black Pawn
        if (pieceName.equals("BlackPawn")) {
            System.out.println("A black pawn is being moved");
            validMove = isBlackPawnMovementValid(e, xLanding, yLanding);
            if (validMove && startY == 10) {
                success = true;
            }
        }

        //King
        else if (pieceName.contains("King")) {
            System.out.println("A king is being moved");
            validMove = isKingMovementValid(e, xLanding, yLanding, xMovement, yMovement, pieceName);
        }

		/*
			White pieces below
		 */

        //White Pawn
        else if (pieceName.equals("WhitePawn")) {
            System.out.println("A white pawn is being moved");
            validMove = isWhitePawnMovementValid(e, xLanding, yLanding);
            //Keep in mind that startY implements promotion functionality
            if (validMove && startY == 6) {
                success = true;
            }
        }

        //Queen
        else if (pieceName.contains("Queen")) {
            System.out.println("A queen is being moved");
            //We are using both bishop movements and rook movements because together they equal the movement of queen movements
            validMove = isBishopMovementValid(e, xLanding, yLanding, xMovement, pieceName) || isRookMovementValid(e, xLanding, yLanding, xMovement, yMovement, pieceName);
        }

        //Bishop
        else if (pieceName.contains("Bishop")) {
            System.out.println("A bishop is being moved");
            validMove = isBishopMovementValid(e, xLanding, yLanding, xMovement, pieceName);
        }

        //Rook
        else if (pieceName.contains("Rook")) {
            System.out.println("A rook is being moved");
            validMove = isRookMovementValid(e, xLanding, yLanding, xMovement, yMovement, pieceName);
        }

        //Knight
        else if (pieceName.contains("Knight")) {
            System.out.println("A knight is being moved");
            validMove = isKnightMovementValid(e, xLanding, yLanding, xMovement, yMovement, pieceName);
        }

		/*
			Procedures when valid/non-valid conditions are met
		 */

        if (!validMove) {
            int location = 0;
            if (startY == 0) {
                location = startX;
            } else {
                location = (startY * 8) + startX;
            }
            String pieceLocation = pieceName + ".png";
            pieces = new JLabel(new ImageIcon(pieceLocation));
            panels = (JPanel) chessBoard.getComponent(location);
            panels.add(pieces);
        } else {
            if (success) {
                int location = 56 + (e.getX() / 75);
                if (c instanceof JLabel) {
                    Container parent = c.getParent();
                    parent.remove(0);
                    pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
                    parent = (JPanel) chessBoard.getComponent(location);
                    parent.add(pieces);
                }
                else {
                    Container parent = (Container) c;
                    pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
                    parent = (JPanel) chessBoard.getComponent(location);
                    parent.add(pieces);
                }
            } else {
                if (c instanceof JLabel) {
                    Container parent = c.getParent();
                    parent.remove(0);
                    parent.add(chessPiece);
                } else {
                    Container parent = (Container) c;
                    parent.add(chessPiece);
                }
                chessPiece.setVisible(true);
            }
        }
    }

    public boolean isPieceInBounds(int xLanding, int yLanding){
        return !(xLanding < 0 || yLanding < 0 || xLanding > 7 || yLanding > 7);
    }

    /*
        Movement functions for chess pieces
     */
    public boolean isBlackPawnMovementValid(MouseEvent e, int xLanding, int yLanding) {
        if(!isPieceInBounds(xLanding, yLanding)){
            return false;
        }
        boolean progression;
        //Pixels divided by square dimensions (75)
        int newY = e.getY() / 75;
        int newX = e.getX() / 75;
        if (startY == 6) {
            if (startX == newX && (((newY - startY) == -1) || ((newY) - startY) == -2)) {
                if ((((newY) - startY) == -2)) {
                    /*
                        Checking for a piece present in the position of the landing,
                        and checking if the landing position does not jump over a piece
                     */
                    if ((!piecePresent(e.getX(), (e.getY()))) && (!piecePresent(e.getX(), (e.getY() + 75)))) {
                        return true;
                    } else {
                        System.out.println("Invalid move!");
                        return false;
                    }
                } else {
                    if ((!piecePresent(e.getX(), (e.getY())))) {
                        return true;
                    } else {
                        System.out.println("Invalid move!");
                        return false;
                    }
                }
            } else if (piecePresent(e.getX(), e.getY()) && ((((newX == (startX + 1) && (startX - 1 <= 7))) || ((newX == (startX - 1)) && (startX - 1 >= 0))))) {
                /*
                    Gets x and y coordinates for white opponent and checks that the opponent is in range for taking
                 */
                if (checkBlackOpponent(e.getX(), e.getY()) && yLanding - startY == -1 && (xLanding - startX == -1 | xLanding - startX == 1)) {
                    return true;
                } else if (checkBlackOpponent(e.getY(), e.getX())) {
                    System.out.println("Invalid move!");
                    return false;
                }
            } else {
                System.out.println("Invalid move!");
                return false;
            }
        } else {
            if ((startX - 1 >= -1) || (startY + 1 <= 2)) {
                if (piecePresent(e.getX(), e.getY()) && ((((newX == (startX + 1) && (startX - 1 <= 7))) || ((newX == (startX - 1)) && (startX - 1 >= 0))))) {
						/*
							Gets x and y coordinates for white opponent and checks that the opponent is in range for taking
						 */
                    if (checkBlackOpponent(e.getX(), e.getY()) && yLanding - startY == -1 && (xLanding - startX == -1 | xLanding - startX == 1)) {
                        return true;
                    } else if (checkBlackOpponent(e.getY(), e.getX())) {
                        System.out.println("Invalid move!");
                        return false;
                    }
                } else {
                    if (!piecePresent(e.getX(), (e.getY()))) {
                        if ((startX == (newX)) && ((newY) - startY) == -1) {
                            return true;
                        } else {
                            System.out.println("Invalid move!");
                            return false;
                        }
                    } else {
                        System.out.println("Invalid move!");
                        return false;
                    }
                }
            } else {
                System.out.println("Invalid move!");
                return false;
            }
        }
        return false;
    }

    public boolean isWhitePawnMovementValid(MouseEvent e, int xLanding, int yLanding) {
        if(!isPieceInBounds(xLanding, yLanding)){
            return false;
        }
        //Pixels divided by square dimensions (75)
        int newY = e.getY() / 75;
        int newX = e.getX() / 75;
        if (startY == 1) {
            if ((startX == newX && (newY - startY) == 1) || (newY - startY) == 2) {
                /* Pawn initial 2 square jump */
                if ((((newY) - startY) == 2)) {
                    /*
                        Checking for a piece present in the position of the landing,
                        and checking if the landing position does not jump over a piece
                     */
                    if ((!piecePresent(e.getX(), (e.getY()))) && (!piecePresent(e.getX(), (e.getY() - 75)))) {
                        return true;
                    } else {
                        System.out.println("Invalid move!");
                        return false;
                    }
                  /* Checking if piece present in the  release of the mouse click */
                } else {
                    if ((!piecePresent(e.getX(), (e.getY())))) {
                        return true;
                    } else {
                        System.out.println("Invalid move!");
                        return false;
                    }
                }
            } else if (piecePresent(e.getX(), e.getY()) && ((((newX == (startX + 1) && (startX + 1 <= 7))) || ((newX == (startX - 1)) && (startX - 1 >= 0))))) {
                /*
					Gets x and y coordinates for white opponent and checks that the opponent is in range for taking
			    */
                if (checkWhiteOpponent(e.getX(), e.getY()) && yLanding - startY == 1 && (xLanding - startX == 1 || xLanding - startX == -1)) {
                    return true;
                } else if (checkWhiteOpponent(e.getY(), e.getX())) {
                    System.out.println("Invalid move!");
                    return false;
                }
            } else {
                System.out.println("Invalid move!");
                return false;
            }
        } else {
            if ((startX - 1 >= -1) || (startY + 1 <= 2)) {
                if (piecePresent(e.getX(), e.getY()) && ((((newX == (startX + 1) && (startX + 1 <= 7))) || ((newX == (startX - 1)) && (startX - 1 >= 0))))) {
						/*
							Gets x and y coordinates for white opponent and checks that the opponent is in range for taking
						 */
                    if (checkWhiteOpponent(e.getX(), e.getY()) && yLanding - startY == 1 && (xLanding - startX == 1 || xLanding - startX == -1)) {
                        return true;
                    } else if (checkWhiteOpponent(e.getY(), e.getX())) {
                        System.out.println("Invalid move!");
                        return false;
                    }
                } else {
                    if (!piecePresent(e.getX(), (e.getY()))) {
                        if ((startX == (newX)) && ((newY) - startY) == 1) {
                            return true;
                        } else {
                            System.out.println("Invalid move!");
                            return false;
                        }
                    } else {
                        System.out.println("Invalid move!");
                        return false;
                    }
                }
            } else {
                System.out.println("Invalid move!");
                return false;
            }
        }
        return false;
    }

    /**
     *
     * @param e
     * @param xLanding
     * @param yLanding
     * @param xMovement
     * @param pieceName
     * @return
     */
    public boolean isBishopMovementValid(MouseEvent e, int xLanding, int yLanding, int xMovement, String pieceName){
        if(!isPieceInBounds(xLanding, yLanding)){
            return false;
        }
        boolean inTheWay = false;
        //Verifying the movement is diagonal
        if(Math.abs(startX - xLanding) == Math.abs(startY - yLanding)){
            //Checking the bottom right diagonal
            if((startX - xLanding < 0) && (startY - yLanding < 0)){
                /*
                    Iterating through diagonal space and checking depth by less than xMovement.
                    yMovement is the same because diagonal coordinate for both x and y are directly proportional
                 */
                for (int i = 0; i < xMovement; i++){
                    if(piecePresent((initialX + (i * 75)), (initialY + (i * 75)))){
                        inTheWay = true;
                    }
                }
            }
            //Checking the top right diagonal
            else if((startX - xLanding < 0) && (startY - yLanding > 0)){
                for (int i = 0; i < xMovement; i++){
                    if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))){
                        inTheWay = true;
                    }
                }
            }
            //Checking the top left diagonal
            else if((startX - xLanding > 0) && (startY - yLanding > 0)){
                for (int i = 0; i < xMovement; i++){
                    if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))){
                        inTheWay = true;
                    }
                }
            }
            //Checking the bottom left diagonal
            else if((startX - xLanding > 0) && (startY - yLanding < 0)){
                for (int i = 0; i < xMovement; i++){
                    if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))){
                        inTheWay = true;
                    }
                }
            }
            if(inTheWay){
                System.out.println("There is a piece in the way!");
                return false;
            }
            else{
                if(piecePresent(e.getX(), (e.getY()))){
                    if(pieceName.contains("White")){
                        if(checkWhiteOpponent(e.getX(), e.getY())){
                            return true;
                        }
                        else{
                            System.out.println("Invalid move!");
                            return false;
                        }
                    }
                    else{
                        if(checkBlackOpponent(e.getX(), e.getY())){
                            return true;
                        }
                        else{
                            System.out.println("Invalid move!");
                            return false;
                        }
                    }
                }
                else{
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isRookMovementValid(MouseEvent e, int xLanding, int yLanding, int xMovement, int yMovement, String pieceName) {
        boolean inTheWay = true;
        if (((xLanding < 0) || (xLanding > 7)) || ((yLanding < 0) || (yLanding > 7))) {
            return false;
        } else {
            //Defines absolute values to allow vertical or horizontal movement
            if (((Math.abs(startX - xLanding) != 0) && (Math.abs(startY - yLanding) == 0)) || ((Math.abs(startX - xLanding) == 0) && (Math.abs(yLanding - startY) != 0))) {
                //Allowing horizontal movement with checks for pieces present along the x-axis when the mouse is released
                if (Math.abs(startX - xLanding) != 0) {
                    //Checking the left horizontal
                    if (startX - xLanding > 0) {
                        for (int i = 0; i < xMovement; i++) {
                            if (piecePresent(initialX - (i * 75), e.getY())) {
                                inTheWay = true;
                                break;
                            } else {
                                inTheWay = false;
                            }
                        }
                    } else {
                        //Checking the right horizontal
                        for (int i = 0; i < xMovement; i++) {
                            if (piecePresent(initialX + (i * 75), e.getY())) {
                                inTheWay = true;
                                break;
                            } else {
                                inTheWay = false;
                            }
                        }
                    }
                }
                //Checking the upwards vertical
                else{
                    if (startY - yLanding > 0){
                        for (int i = 0; i < yMovement; i++){
                            if (piecePresent(e.getX(), initialY - (i * 75))){
                                inTheWay = true;
                                break;
                            }
                            else{
                                inTheWay = false;
                            }
                        }
                    }
                    //Checking the downwards vertical
                    else{
                        for (int i = 0; i < yMovement;i++){
                            if (piecePresent(e.getX(), initialY + (i * 75))){
                                inTheWay = true;
                                break;
                            }
                            else{
                                inTheWay = false;
                            }
                        }
                    }
                }
                //If there is a piece in the way we return the move as invalid
                if (inTheWay){
                    return false;
                }
                //Identifying the colour of the piece. Of course, we cannot take our own piece
                else{
                    if (piecePresent(e.getX(), (e.getY()))){
                        if (pieceName.contains("White")){
                            if(checkWhiteOpponent(e.getX(), e.getY())){
                                return true;
                            }
                            else{
                                return true;
                            }
                        }
                        else{
                            if (checkBlackOpponent(e.getX(), e.getY())){
                                return true;
                            }
                            else {
                                return false;
                            }
                        }
                    }
                    else{
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public boolean isKnightMovementValid(MouseEvent e, int xLanding, int yLanding, int xMovement, int yMovement, String pieceName) {
        if(!isPieceInBounds(xLanding, yLanding)){
            return false;
        }
        //pixels divided by square dimensions (75)
        int newY = e.getY() / 75;
        int newX = e.getX() / 75;
        //Movements allowed
        if (((xMovement == 1) && (yMovement == 2)) || ((xMovement == 2) && (yMovement == 1))) {
            //If no piece present move is valid
            if (!piecePresent(e.getX(), e.getY())) {
                return true;
            } else{
                //A piece is present, but check if it is a white opponent and if so set valid move to true
                if(pieceName.contains("White")){
                    return checkWhiteOpponent(e.getX(), e.getY());
                }
                //Check if piece is black, if so valid move is also equal to true
                else if (pieceName.contains("Black")){
                    return checkBlackOpponent(e.getX(), e.getY());
                }
            }
        }
        return false;
    }

    public boolean isKingMovementValid(MouseEvent e, int xLanding, int yLanding, int xMovement, int yMovement, String pieceName){
        int newY = e.getY() / 75;
        int newX = e.getX() / 75;
        if(!isPieceInBounds(xLanding, yLanding)){
            return false;
        }
        if((startX - xLanding >= -1 && startX - xLanding <= 1) && (startY - yLanding >= -1 && startY - yLanding <= 1)){
            //Checking the top right diagonal
            if((startX - xLanding < 0) && (startY - yLanding > 0)) {
                System.out.println("Moving top right diagonal");
                if (piecePresent(newX, (newY - 1))) {
                    if (pieceName.contains("King")) {
                        return checkBlackOpponent(e.getX(), e.getY());
                    }
                    else if (pieceName.contains("King")){
                        return checkWhiteOpponent(e.getX(), e.getY());
                    }
                    else{
                        return true;
                    }
                }
//                if (piecePresent((e.getX() + 1), (e.getY() - 1))) {
//                    if (pieceName.contains("King")) {
//                        return checkBlackOpponent(e.getX() + 1, e.getY() - 1);
//                    }
//                    else if (pieceName.contains("King")){
//                        return checkWhiteOpponent(e.getX() + 1, e.getY() - 1);
//                    }
//                    else{
//                        return true;
//                    }
//                }
//                if (piecePresent((e.getX() + 1), e.getY())) {
//                    if (pieceName.contains("King")) {
//                        return checkBlackOpponent(e.getX() + 1, e.getY());
//                    }
//                    else if (pieceName.contains("King")){
//                        return checkWhiteOpponent(e.getX() + 1, e.getY());
//                    }
//                    else{
//                        return true;
//                    }
//                }
            } else if((startX - xLanding < 0) && (startY - yLanding < 0)){
                //Checking the bottom right diagonal
                System.out.println("Moving bottom right diagonal");
                if (piecePresent(newX + 1, newY)){
                    if(pieceName.contains("King")){
                        System.out.println("You cannot move into check!");
                        return false;
                    }
                    else{
                        return true;
                    }
                }
                if (piecePresent(newX + 1, newY + 1)){
                    if(pieceName.contains("King")){
                        System.out.println("You cannot move into check!");
                        return false;
                    }
                    else{
                        return true;
                    }
                }
                if (piecePresent(newX, newY + 1)){
                    if(pieceName.contains("King")){
                        System.out.println("You cannot move into check!");
                        return false;
                    }
                    else{
                        return true;
                    }
                }
            } else if((startX - xLanding > 0) && (startY - yLanding > 0)){
                //Checking the top left diagonal
                System.out.println("Moving top left diagonal");
                if (piecePresent(newX - 1, newY)){
                    if(pieceName.contains("King")){
                        System.out.println("You cannot move into check!");
                        return false;
                    }
                    else{
                        return true;
                    }
                }
                if (piecePresent(newX - 1, newY + 1)){
                    if(pieceName.contains("King")){
                        System.out.println("You cannot move into check!");
                        return false;
                    }
                    else{
                        return true;
                    }
                }
                if (piecePresent(newX, newY + 1)){
                    if(pieceName.contains("King")){
                        System.out.println("You cannot move into check!");
                        return false;
                    }
                    else{
                        return true;
                    }
                }
            } else if ((startX - xLanding > 0) && (startY - yLanding < 0)) {
                //Checking the bottom left diagonal
                System.out.println("Moving bottom left diagonal");
                if (piecePresent(newX - 1, newY)){
                    if(pieceName.contains("King")){
                        System.out.println("You cannot move into check!");
                        return false;
                    }
                    else{
                        return true;
                    }
                }
                if (piecePresent(newX - 1, newY + 1)){
                    if(pieceName.contains("King")){
                        System.out.println("You cannot move into check!");
                        return false;
                    }
                    else{
                        return true;
                    }
                }
                if (piecePresent(newX, newY - 1)){
                    if(pieceName.contains("King")){
                        System.out.println("You cannot move into check!");
                        return false;
                    }
                    else{
                        return true;
                    }
                }
            }
            else if (!piecePresent(e.getX(), e.getY())) {
                return true;
            } else{
                //A piece is present, but check if it is a white opponent and if so set valid move to true
                if (pieceName.contains("White")) {
                    return checkWhiteOpponent(e.getX(), e.getY());
                }
                //Check if piece is black, if so valid move is also equal to true
                else if (pieceName.contains("Black")) {
                    return checkBlackOpponent(e.getX(), e.getY());
                }
            }
        } else{
            System.out.println("Invalid move!");
            return false;
        }
        return false;
    }

public void mouseClicked(MouseEvent e){

        }

public void mouseMoved(MouseEvent e){

        }

public void mouseEntered(MouseEvent e){

        }

public void mouseExited(MouseEvent e){

        }
}


