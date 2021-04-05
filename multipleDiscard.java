public abstract class multipleDiscard throws InvalidColorSubmissionException, InvalidValueSubmissionException {
    if ((card.getColors() != validColor) && (card.getValues() == validValue)) {
        String message = "Invalid player move, expected color: " + validColor + " but got color " + card.getColors();
        String message2 = "Invalid player move, expected color: " + validValue + " but got color " + card.getValues();
        // message.setFont(new Font("Arial", Font.BOLD, 48));
        // JOptionPane.showMessageDialog(null, message);
        throw new InvalidColorSubmissionException(message, card.getColors(), validColor);
        // message2.setFont(new Font("Arial", Font.BOLD, 48));
        // JOptionPane.showMessageDialog(null, message2);
        // throw new InvalidValueSubmissionException(message2, card.getValues(), validValue);
        
    }
    else if ((card.getColors() == validColor) && (card.getValues() != validValue)) {
        String message = "Invalid player move, expected color: " + validColor + " but got color " + card.getColors();
        String message2 = "Invalid player move, expected color: " + validValue + " but got color " + card.getValues();
        throw new InvalidValueSubmissionException(message2, card.getValues(), validValue);
    }
}
