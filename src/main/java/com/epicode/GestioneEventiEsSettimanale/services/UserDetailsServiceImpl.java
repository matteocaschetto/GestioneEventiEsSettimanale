package com.epicode.GestioneEventiEsSettimanale.services;

import com.epicode.GestioneEventiEsSettimanale.model.Utente;
import com.epicode.GestioneEventiEsSettimanale.repository.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UtenteRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User non trovato con username: " + username);
        }

        return UserDetailsImpl.build(user);
    }

}