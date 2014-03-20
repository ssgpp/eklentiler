/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2009 Onur Derin  All rights reserved.
 *
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 *  are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above
 *       copyright notice, this list of conditions and the following
 *       disclaimer in the documentation and/or other materials provided
 *       with the distribution.
 *     * Neither the name of the <ORGANIZATION> nor the names of its
 *       contributors may be used to endorse or promote products derived
 *       from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package sacre.plugins.comps;

import ch.alari.sacre.Component;
import ch.alari.sacre.Port;
import ch.alari.sacre.Token;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;

/**
 *
 * @author Onur Derin <oderin at users.sourceforge.net>
 */

// Bütün ssg++ bileşenleri Component sınıfından türetilir.
public class OrnekEklentiBilesen extends Component 
{
    // iş hattındaki parametrelerin doğruluğunu belirtir.
    protected boolean initSuccess;
    
    // Tüm Component'ler için constructor imzası bu şekildedir.
    public OrnekEklentiBilesen(String name, Map<String, String> parameters)
    {
        super(name);
        // Bileşenin iş hatlarında kullanılan adı.
        setType("ÖrnekEklentiBileşen");
        // Bileşenin giriş ve çıkış kapıları tipleri, adları ve adetleriyle belirlenmelidir.
        addPort(new Port<Token>(Token.class, "in", Port.DIR_TYPE_IN));
        addPort(new Port<Token>(Token.class, "out", Port.DIR_TYPE_OUT));
        
        initSuccess = true;
    }
    
    // Thread olarak çalıştırılan bileşenlerin yaptıkları işleri bu metod belirler.
    public void task() throws InterruptedException
    {
        // Bileşin sonlanması için çıkış kapılarına özel STOP token gönderilmeli, durumu STOPPED'a atanmalı ve InterruptedException atılmalıdır.
        if(!initSuccess)
        {
            port("out").put(new Token(Token.STOP));
            state = State.STOPPED;
            throw new InterruptedException();
        }
        
        // Giriş kapısından bir token okunması
        Token currToken = (Token)port("in").take();
        
	// Okunan token STOP tokeni ise bileşenin çalışması durdurulur.
        if(currToken.isStop())
        {            
            port("out").put(new Token(Token.STOP));
            state = State.STOPPED;
            return;
        }

	// Gelen token ile birşeyler yapıp çıktıya yazılır.
        // ...
 
        // Bu örnekte okunan token üstünde hiçbir işlem yapmadan olduğu gibi çıkış kapısına gönderiyoruz.
	port("out").put(currToken);
    }
}
