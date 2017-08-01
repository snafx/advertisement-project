package ogloszenia.model;

import javax.persistence.*;

@Entity
public class ConversationMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer id;

    @Column(nullable=false)
    private String messageContent;

    @ManyToOne(cascade = CascadeType.ALL) //dodaje nam kaskadowo do bazy, najpierw conversation, potem converMeasage
    @JoinColumn
    private Conversation conversation;

    @ManyToOne
    @JoinColumn
    private User owner; //author wiadomosci

    public ConversationMessage() {
    }

    public ConversationMessage(String messageContent, Conversation conversation, User owner) {
        this.messageContent = messageContent;
        this.conversation = conversation;
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
}
