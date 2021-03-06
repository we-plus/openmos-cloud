package eu.openmos.agentcloud.agent.responders;

import eu.openmos.agentcloud.optimizer.PluggableOptimizerInterface;
import jade.core.Agent;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IsSubSystemRampUpChangeStagePossibleResponder extends AchieveREResponder {

        private PluggableOptimizerInterface optimizationAlgorithm;
        Logger logger = LoggerFactory.getLogger(IsSubSystemRampUpChangeStagePossibleResponder.class);

        public IsSubSystemRampUpChangeStagePossibleResponder(
                Agent a, 
                PluggableOptimizerInterface optimizationAlgorithm, 
                MessageTemplate mt
        ) 
        {
            super(a, mt);
            this.optimizationAlgorithm = optimizationAlgorithm;
        }

        @Override
        protected ACLMessage handleRequest(ACLMessage request) throws NotUnderstoodException, RefuseException {
            String subSystemId = request.getContent();
            
            boolean isChangeStagePossible = optimizationAlgorithm.isSubSystemRampUpChangeStagePossible(subSystemId);
            
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            try {
                reply.setContentObject((Boolean)isChangeStagePossible);
                logger.debug(getClass().getName() + " - handleRequest - " + isChangeStagePossible);
            } catch (IOException ex) {
                throw new RefuseException(ex.getLocalizedMessage());                
            }
            return reply;
        }
    }
