package ogloszenia.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ConversationMessage {

    /**
     * Wiadomość - pojedyncza wiadomosc przypisana o danej konwersacji
     * id
     * id konwersacji (caly obiekt)
     * text
     * autor (autorem bedzie albo nadawca konwersacji albo odbiorca konwesacji: jedna z tym dwoch osob)
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer id;

    @Column(nullable=false)
    private String messageContent;

    //jesli przy dodawaniu nowej wiadomosci konwersacja nie bedzie istniala, to najpierw ja doda, a dopiero potem doda wiadomosc
    @ManyToOne(cascade = CascadeType.ALL) //dodaje nam kaskadowo do bazy, najpierw conversation, potem converMeasage
    @JoinColumn
    private Conversation conversation;

    @ManyToOne
    @JoinColumn
    private User owner; //author wiadomosci

    @Column(nullable = false)
    LocalDate createDate;

    public ConversationMessage() {

    }

    public ConversationMessage(String messageContent, Conversation conversation, User owner) {

        this.messageContent = messageContent;
        this.conversation = conversation;
        this.owner = owner;
        this.createDate = LocalDate.now();
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

}
