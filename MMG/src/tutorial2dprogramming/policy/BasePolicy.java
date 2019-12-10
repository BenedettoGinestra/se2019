/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.policy;

import tutorial2dprogramming.entities.Creature;
import tutorial2dprogramming.entities.state.MovementState;

/**
 *
 * @author mario
 */
public abstract class Policy {
    
    public abstract MovementState getMovement();
    
}
