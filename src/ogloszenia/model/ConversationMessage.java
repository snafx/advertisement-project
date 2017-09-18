package ogloszenia.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ConversationMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer id;

    @Column(nullable = false)
    private String messageContent;

    //jesli przy dodawaniu nowej wiadomosci konwersacja nie bedzie istniala, to najpierw ja doda, a dopiero potem doda wiadomosc
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    Conversation conversation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    User owner;

    @Column(nullable = false)
    LocalDate createDate;

    public ConversationMessage() {
    }

    public ConversationMessage(String messageContent, Conversation conversation) {
        this.messageContent = messageContent;
        this.conversation = conversation;
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
