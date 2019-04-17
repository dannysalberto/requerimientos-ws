package co.com.interkont.wscobra.dto;

import java.io.Serializable;
import java.math.BigDecimal;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

@Entity
@Table(catalog = "cobrauepruebas", schema = "encuestasue")
public class FuncionIndicadoresEncuestas implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_obra")
    private Integer id;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_num_ind_2")
    private BigDecimal pNumInd2;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_den_ind_2")
    private BigDecimal pDenInd2;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_ind_2")
    private BigDecimal pInd2;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_base_ind_2")
    private BigDecimal pBaseInd2;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_esperado_ind_2")
    private BigDecimal pEsperadoInd2;
    @Column(name = "p_num_ind_3")
    private BigDecimal pNumInd3;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_den_ind_3")
    private BigDecimal pDenInd3;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_ind_3")
    private BigDecimal pInd3;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_base_ind_3")
    private BigDecimal pBaseInd3;
    @Column(name = "p_esperado_ind_3")
    private BigDecimal pEsperadoInd3;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_num_ind_4")
    private BigDecimal pNumInd4;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_den_ind_4")
    private BigDecimal pDenInd4;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_ind_4")
    private BigDecimal pInd4;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_base_ind_4")
    private BigDecimal pBaseInd4;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_esperado_ind_4")
    private BigDecimal pEsperadoInd4;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_num_ind_5")
    private BigDecimal pNumInd5;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_den_ind_5")
    private BigDecimal pDenInd5;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_ind_5")
    private BigDecimal pInd5;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_base_ind_5")
    private BigDecimal pBaseInd5;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_esperado_ind_5")
    private BigDecimal pEsperadoInd5;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_num_ind_6")
    private BigDecimal pNumInd6;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_den_ind_6")
    private BigDecimal pDenInd6;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_ind_6")
    private BigDecimal pInd6;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_base_ind_6")
    private BigDecimal pBaseInd6;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_esperado_ind_6")
    private BigDecimal pEsperadoInd6;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_num_ind_7")
    private BigDecimal pNumInd7;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_den_ind_7")
    private BigDecimal pDenInd7;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_ind_7")
    private BigDecimal pInd7;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_base_ind_7")
    private BigDecimal pBaseInd7;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_esperado_ind_7")
    private BigDecimal pEsperadoInd7;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_num_ind_8")
    private BigDecimal pNumInd8;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_den_ind_8")
    private BigDecimal pDenInd8;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_ind_8")
    private BigDecimal pInd8;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_base_ind_8")
    private BigDecimal pBaseInd8;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_esperado_ind_8")
    private BigDecimal pEsperadoInd8;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_num_ind_9")
    private BigDecimal pNumInd9;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_den_ind_9")
    private BigDecimal pDenInd9;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_ind_9")
    private BigDecimal pInd9;
    @Column(name = "p_base_ind_9")
    private BigDecimal pBaseInd9;
    @Column(name = "p_esperado_ind_9")
    private BigDecimal pEsperadoInd9;
    @Column(name = "p_num_ind_10")
    private BigDecimal pNumInd10;
    @Column(name = "p_den_ind_10")
    private BigDecimal pDenInd10;
    @Column(name = "p_ind_10")
    private BigDecimal pInd10;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_base_ind_10")
    private BigDecimal pBaseInd10;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_esperado_ind_10")
    private BigDecimal pEsperadoInd10;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_num_ind_11")
    private BigDecimal pNumInd11;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_den_ind_11")
    private BigDecimal pDenInd11;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_ind_11")
    private BigDecimal pInd11;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_base_ind_11")
    private BigDecimal pBaseInd11;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_esperado_ind_11")
    private BigDecimal pEsperadoInd11;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_num_ind_12")
    private BigDecimal pNumInd12;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_den_ind_12")
    private BigDecimal pDenInd12;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_ind_12")
    private BigDecimal pInd12;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_base_ind_12")
    private BigDecimal pBaseInd12;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_esperado_ind_12")
    private BigDecimal pEsperadoInd12;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_num_ind_13")
    private BigDecimal pNumInd13;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_den_ind_13")
    private BigDecimal pDenInd13;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_ind_13")
    private BigDecimal pInd13;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_base_ind_13")
    private BigDecimal pBaseInd13;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_esperado_ind_13")
    private BigDecimal pEsperadoInd13;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_num_ind_org_1")
    private BigDecimal pNumIndOrg1;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_den_ind_org_1")
    private BigDecimal pDenIndOrg1;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_ind_org_1")
    private BigDecimal pIndOrg1;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_base_ind_org_1")
    private BigDecimal pBaseIndOrg1;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_esperado_ind_org_1")
    private BigDecimal pEsperadoIndOrg1;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_num_ind_org_2")
    private BigDecimal pNumIndOrg2;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_den_ind_org_2")
    private BigDecimal pDenIndOrg2;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_ind_org_2")
    private BigDecimal pIndOrg2;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_base_ind_org_2")
    private BigDecimal pBaseIndOrg2;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_esperado_ind_org_2")
    private BigDecimal pEsperadoIndOrg2;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_num_ind_org_3")
    private BigDecimal pNumIndOrg3;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_den_ind_org_3")
    private BigDecimal pDenIndOrg3;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_ind_org_3")
    private BigDecimal pIndOrg3;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_base_ind_org_3")
    private BigDecimal pBaseIndOrg3;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_esperado_ind_org_3")
    private BigDecimal pEsperadoIndOrg3;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_num_ind_org_4")
    private BigDecimal pNumIndOrg4;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_den_ind_org_4")
    private BigDecimal pDenIndOrg4;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_ind_org_4")
    private BigDecimal pIndOrg4;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_base_ind_org_4")
    private BigDecimal pBaseIndOrg4;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_esperado_ind_org_4")
    private BigDecimal pEsperadoIndOrg4;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_num_ind_org_5")
    private BigDecimal pNumIndOrg5;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_den_ind_org_5")
    private BigDecimal pDenIndOrg5;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_ind_org_5")
    private BigDecimal pIndOrg5;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_base_ind_org_5")
    private BigDecimal pBaseIndOrg5;
    @Digits(integer=9, fraction=1)
    @Column(name = "p_esperado_ind_org_5")
    private BigDecimal pEsperadoIndOrg5;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getpNumInd2() {
        return pNumInd2;
    }

    public void setpNumInd2(BigDecimal pNumInd2) {
        this.pNumInd2 = pNumInd2;
    }

    public BigDecimal getpDenInd2() {
        return pDenInd2;
    }

    public void setpDenInd2(BigDecimal pDenInd2) {
        this.pDenInd2 = pDenInd2;
    }

    public BigDecimal getpInd2() {
        return pInd2;
    }

    public void setpInd2(BigDecimal pInd2) {
        this.pInd2 = pInd2;
    }

    public BigDecimal getpBaseInd2() {
        return pBaseInd2;
    }

    public void setpBaseInd2(BigDecimal pBaseInd2) {
        this.pBaseInd2 = pBaseInd2;
    }

    public BigDecimal getpEsperadoInd2() {
        return pEsperadoInd2;
    }

    public void setpEsperadoInd2(BigDecimal pEsperadoInd2) {
        this.pEsperadoInd2 = pEsperadoInd2;
    }

    public BigDecimal getpNumInd3() {
        return pNumInd3;
    }

    public void setpNumInd3(BigDecimal pNumInd3) {
        this.pNumInd3 = pNumInd3;
    }

    public BigDecimal getpDenInd3() {
        return pDenInd3;
    }

    public void setpDenInd3(BigDecimal pDenInd3) {
        this.pDenInd3 = pDenInd3;
    }

    public BigDecimal getpInd3() {
        return pInd3;
    }

    public void setpInd3(BigDecimal pInd3) {
        this.pInd3 = pInd3;
    }

    public BigDecimal getpBaseInd3() {
        return pBaseInd3;
    }

    public void setpBaseInd3(BigDecimal pBaseInd3) {
        this.pBaseInd3 = pBaseInd3;
    }

    public BigDecimal getpEsperadoInd3() {
        return pEsperadoInd3;
    }

    public void setpEsperadoInd3(BigDecimal pEsperadoInd3) {
        this.pEsperadoInd3 = pEsperadoInd3;
    }

    public BigDecimal getpNumInd4() {
        return pNumInd4;
    }

    public void setpNumInd4(BigDecimal pNumInd4) {
        this.pNumInd4 = pNumInd4;
    }

    public BigDecimal getpDenInd4() {
        return pDenInd4;
    }

    public void setpDenInd4(BigDecimal pDenInd4) {
        this.pDenInd4 = pDenInd4;
    }

    public BigDecimal getpInd4() {
        return pInd4;
    }

    public void setpInd4(BigDecimal pInd4) {
        this.pInd4 = pInd4;
    }

    public BigDecimal getpBaseInd4() {
        return pBaseInd4;
    }

    public void setpBaseInd4(BigDecimal pBaseInd4) {
        this.pBaseInd4 = pBaseInd4;
    }

    public BigDecimal getpEsperadoInd4() {
        return pEsperadoInd4;
    }

    public void setpEsperadoInd4(BigDecimal pEsperadoInd4) {
        this.pEsperadoInd4 = pEsperadoInd4;
    }

    public BigDecimal getpNumInd5() {
        return pNumInd5;
    }

    public void setpNumInd5(BigDecimal pNumInd5) {
        this.pNumInd5 = pNumInd5;
    }

    public BigDecimal getpDenInd5() {
        return pDenInd5;
    }

    public void setpDenInd5(BigDecimal pDenInd5) {
        this.pDenInd5 = pDenInd5;
    }

    public BigDecimal getpInd5() {
        return pInd5;
    }

    public void setpInd5(BigDecimal pInd5) {
        this.pInd5 = pInd5;
    }

    public BigDecimal getpBaseInd5() {
        return pBaseInd5;
    }

    public void setpBaseInd5(BigDecimal pBaseInd5) {
        this.pBaseInd5 = pBaseInd5;
    }

    public BigDecimal getpEsperadoInd5() {
        return pEsperadoInd5;
    }

    public void setpEsperadoInd5(BigDecimal pEsperadoInd5) {
        this.pEsperadoInd5 = pEsperadoInd5;
    }

    public BigDecimal getpNumInd6() {
        return pNumInd6;
    }

    public void setpNumInd6(BigDecimal pNumInd6) {
        this.pNumInd6 = pNumInd6;
    }

    public BigDecimal getpDenInd6() {
        return pDenInd6;
    }

    public void setpDenInd6(BigDecimal pDenInd6) {
        this.pDenInd6 = pDenInd6;
    }

    public BigDecimal getpInd6() {
        return pInd6;
    }

    public void setpInd6(BigDecimal pInd6) {
        this.pInd6 = pInd6;
    }

    public BigDecimal getpBaseInd6() {
        return pBaseInd6;
    }

    public void setpBaseInd6(BigDecimal pBaseInd6) {
        this.pBaseInd6 = pBaseInd6;
    }

    public BigDecimal getpEsperadoInd6() {
        return pEsperadoInd6;
    }

    public void setpEsperadoInd6(BigDecimal pEsperadoInd6) {
        this.pEsperadoInd6 = pEsperadoInd6;
    }

    public BigDecimal getpNumInd7() {
        return pNumInd7;
    }

    public void setpNumInd7(BigDecimal pNumInd7) {
        this.pNumInd7 = pNumInd7;
    }

    public BigDecimal getpDenInd7() {
        return pDenInd7;
    }

    public void setpDenInd7(BigDecimal pDenInd7) {
        this.pDenInd7 = pDenInd7;
    }

    public BigDecimal getpInd7() {
        return pInd7;
    }

    public void setpInd7(BigDecimal pInd7) {
        this.pInd7 = pInd7;
    }

    public BigDecimal getpBaseInd7() {
        return pBaseInd7;
    }

    public void setpBaseInd7(BigDecimal pBaseInd7) {
        this.pBaseInd7 = pBaseInd7;
    }

    public BigDecimal getpEsperadoInd7() {
        return pEsperadoInd7;
    }

    public void setpEsperadoInd7(BigDecimal pEsperadoInd7) {
        this.pEsperadoInd7 = pEsperadoInd7;
    }

    public BigDecimal getpNumInd8() {
        return pNumInd8;
    }

    public void setpNumInd8(BigDecimal pNumInd8) {
        this.pNumInd8 = pNumInd8;
    }

    public BigDecimal getpDenInd8() {
        return pDenInd8;
    }

    public void setpDenInd8(BigDecimal pDenInd8) {
        this.pDenInd8 = pDenInd8;
    }

    public BigDecimal getpInd8() {
        return pInd8;
    }

    public void setpInd8(BigDecimal pInd8) {
        this.pInd8 = pInd8;
    }

    public BigDecimal getpBaseInd8() {
        return pBaseInd8;
    }

    public void setpBaseInd8(BigDecimal pBaseInd8) {
        this.pBaseInd8 = pBaseInd8;
    }

    public BigDecimal getpEsperadoInd8() {
        return pEsperadoInd8;
    }

    public void setpEsperadoInd8(BigDecimal pEsperadoInd8) {
        this.pEsperadoInd8 = pEsperadoInd8;
    }

    public BigDecimal getpNumInd9() {
        return pNumInd9;
    }

    public void setpNumInd9(BigDecimal pNumInd9) {
        this.pNumInd9 = pNumInd9;
    }

    public BigDecimal getpDenInd9() {
        return pDenInd9;
    }

    public void setpDenInd9(BigDecimal pDenInd9) {
        this.pDenInd9 = pDenInd9;
    }

    public BigDecimal getpInd9() {
        return pInd9;
    }

    public void setpInd9(BigDecimal pInd9) {
        this.pInd9 = pInd9;
    }

    public BigDecimal getpBaseInd9() {
        return pBaseInd9;
    }

    public void setpBaseInd9(BigDecimal pBaseInd9) {
        this.pBaseInd9 = pBaseInd9;
    }

    public BigDecimal getpEsperadoInd9() {
        return pEsperadoInd9;
    }

    public void setpEsperadoInd9(BigDecimal pEsperadoInd9) {
        this.pEsperadoInd9 = pEsperadoInd9;
    }

    public BigDecimal getpNumInd10() {
        return pNumInd10;
    }

    public void setpNumInd10(BigDecimal pNumInd10) {
        this.pNumInd10 = pNumInd10;
    }

    public BigDecimal getpDenInd10() {
        return pDenInd10;
    }

    public void setpDenInd10(BigDecimal pDenInd10) {
        this.pDenInd10 = pDenInd10;
    }

    public BigDecimal getpInd10() {
        return pInd10;
    }

    public void setpInd10(BigDecimal pInd10) {
        this.pInd10 = pInd10;
    }

    public BigDecimal getpBaseInd10() {
        return pBaseInd10;
    }

    public void setpBaseInd10(BigDecimal pBaseInd10) {
        this.pBaseInd10 = pBaseInd10;
    }

    public BigDecimal getpEsperadoInd10() {
        return pEsperadoInd10;
    }

    public void setpEsperadoInd10(BigDecimal pEsperadoInd10) {
        this.pEsperadoInd10 = pEsperadoInd10;
    }

    public BigDecimal getpNumInd11() {
        return pNumInd11;
    }

    public void setpNumInd11(BigDecimal pNumInd11) {
        this.pNumInd11 = pNumInd11;
    }

    public BigDecimal getpDenInd11() {
        return pDenInd11;
    }

    public void setpDenInd11(BigDecimal pDenInd11) {
        this.pDenInd11 = pDenInd11;
    }

    public BigDecimal getpInd11() {
        return pInd11;
    }

    public void setpInd11(BigDecimal pInd11) {
        this.pInd11 = pInd11;
    }

    public BigDecimal getpBaseInd11() {
        return pBaseInd11;
    }

    public void setpBaseInd11(BigDecimal pBaseInd11) {
        this.pBaseInd11 = pBaseInd11;
    }

    public BigDecimal getpEsperadoInd11() {
        return pEsperadoInd11;
    }

    public void setpEsperadoInd11(BigDecimal pEsperadoInd11) {
        this.pEsperadoInd11 = pEsperadoInd11;
    }

    public BigDecimal getpNumInd12() {
        return pNumInd12;
    }

    public void setpNumInd12(BigDecimal pNumInd12) {
        this.pNumInd12 = pNumInd12;
    }

    public BigDecimal getpDenInd12() {
        return pDenInd12;
    }

    public void setpDenInd12(BigDecimal pDenInd12) {
        this.pDenInd12 = pDenInd12;
    }

    public BigDecimal getpInd12() {
        return pInd12;
    }

    public void setpInd12(BigDecimal pInd12) {
        this.pInd12 = pInd12;
    }

    public BigDecimal getpBaseInd12() {
        return pBaseInd12;
    }

    public void setpBaseInd12(BigDecimal pBaseInd12) {
        this.pBaseInd12 = pBaseInd12;
    }

    public BigDecimal getpEsperadoInd12() {
        return pEsperadoInd12;
    }

    public void setpEsperadoInd12(BigDecimal pEsperadoInd12) {
        this.pEsperadoInd12 = pEsperadoInd12;
    }

    public BigDecimal getpNumInd13() {
        return pNumInd13;
    }

    public void setpNumInd13(BigDecimal pNumInd13) {
        this.pNumInd13 = pNumInd13;
    }

    public BigDecimal getpDenInd13() {
        return pDenInd13;
    }

    public void setpDenInd13(BigDecimal pDenInd13) {
        this.pDenInd13 = pDenInd13;
    }

    public BigDecimal getpInd13() {
        return pInd13;
    }

    public void setpInd13(BigDecimal pInd13) {
        this.pInd13 = pInd13;
    }

    public BigDecimal getpBaseInd13() {
        return pBaseInd13;
    }

    public void setpBaseInd13(BigDecimal pBaseInd13) {
        this.pBaseInd13 = pBaseInd13;
    }

    public BigDecimal getpEsperadoInd13() {
        return pEsperadoInd13;
    }

    public void setpEsperadoInd13(BigDecimal pEsperadoInd13) {
        this.pEsperadoInd13 = pEsperadoInd13;
    }

    public BigDecimal getpNumIndOrg1() {
        return pNumIndOrg1;
    }

    public void setpNumIndOrg1(BigDecimal pNumIndOrg1) {
        this.pNumIndOrg1 = pNumIndOrg1;
    }

    public BigDecimal getpDenIndOrg1() {
        return pDenIndOrg1;
    }

    public void setpDenIndOrg1(BigDecimal pDenIndOrg1) {
        this.pDenIndOrg1 = pDenIndOrg1;
    }

    public BigDecimal getpIndOrg1() {
        return pIndOrg1;
    }

    public void setpIndOrg1(BigDecimal pIndOrg1) {
        this.pIndOrg1 = pIndOrg1;
    }

    public BigDecimal getpBaseIndOrg1() {
        return pBaseIndOrg1;
    }

    public void setpBaseIndOrg1(BigDecimal pBaseIndOrg1) {
        this.pBaseIndOrg1 = pBaseIndOrg1;
    }

    public BigDecimal getpEsperadoIndOrg1() {
        return pEsperadoIndOrg1;
    }

    public void setpEsperadoIndOrg1(BigDecimal pEsperadoIndOrg1) {
        this.pEsperadoIndOrg1 = pEsperadoIndOrg1;
    }

    public BigDecimal getpNumIndOrg2() {
        return pNumIndOrg2;
    }

    public void setpNumIndOrg2(BigDecimal pNumIndOrg2) {
        this.pNumIndOrg2 = pNumIndOrg2;
    }

    public BigDecimal getpDenIndOrg2() {
        return pDenIndOrg2;
    }

    public void setpDenIndOrg2(BigDecimal pDenIndOrg2) {
        this.pDenIndOrg2 = pDenIndOrg2;
    }

    public BigDecimal getpIndOrg2() {
        return pIndOrg2;
    }

    public void setpIndOrg2(BigDecimal pIndOrg2) {
        this.pIndOrg2 = pIndOrg2;
    }

    public BigDecimal getpBaseIndOrg2() {
        return pBaseIndOrg2;
    }

    public void setpBaseIndOrg2(BigDecimal pBaseIndOrg2) {
        this.pBaseIndOrg2 = pBaseIndOrg2;
    }

    public BigDecimal getpEsperadoIndOrg2() {
        return pEsperadoIndOrg2;
    }

    public void setpEsperadoIndOrg2(BigDecimal pEsperadoIndOrg2) {
        this.pEsperadoIndOrg2 = pEsperadoIndOrg2;
    }

    public BigDecimal getpNumIndOrg3() {
        return pNumIndOrg3;
    }

    public void setpNumIndOrg3(BigDecimal pNumIndOrg3) {
        this.pNumIndOrg3 = pNumIndOrg3;
    }

    public BigDecimal getpDenIndOrg3() {
        return pDenIndOrg3;
    }

    public void setpDenIndOrg3(BigDecimal pDenIndOrg3) {
        this.pDenIndOrg3 = pDenIndOrg3;
    }

    public BigDecimal getpIndOrg3() {
        return pIndOrg3;
    }

    public void setpIndOrg3(BigDecimal pIndOrg3) {
        this.pIndOrg3 = pIndOrg3;
    }

    public BigDecimal getpBaseIndOrg3() {
        return pBaseIndOrg3;
    }

    public void setpBaseIndOrg3(BigDecimal pBaseIndOrg3) {
        this.pBaseIndOrg3 = pBaseIndOrg3;
    }

    public BigDecimal getpEsperadoIndOrg3() {
        return pEsperadoIndOrg3;
    }

    public void setpEsperadoIndOrg3(BigDecimal pEsperadoIndOrg3) {
        this.pEsperadoIndOrg3 = pEsperadoIndOrg3;
    }

    public BigDecimal getpNumIndOrg4() {
        return pNumIndOrg4;
    }

    public void setpNumIndOrg4(BigDecimal pNumIndOrg4) {
        this.pNumIndOrg4 = pNumIndOrg4;
    }

    public BigDecimal getpDenIndOrg4() {
        return pDenIndOrg4;
    }

    public void setpDenIndOrg4(BigDecimal pDenIndOrg4) {
        this.pDenIndOrg4 = pDenIndOrg4;
    }

    public BigDecimal getpIndOrg4() {
        return pIndOrg4;
    }

    public void setpIndOrg4(BigDecimal pIndOrg4) {
        this.pIndOrg4 = pIndOrg4;
    }

    public BigDecimal getpBaseIndOrg4() {
        return pBaseIndOrg4;
    }

    public void setpBaseIndOrg4(BigDecimal pBaseIndOrg4) {
        this.pBaseIndOrg4 = pBaseIndOrg4;
    }

    public BigDecimal getpEsperadoIndOrg4() {
        return pEsperadoIndOrg4;
    }

    public void setpEsperadoIndOrg4(BigDecimal pEsperadoIndOrg4) {
        this.pEsperadoIndOrg4 = pEsperadoIndOrg4;
    }

    public BigDecimal getpNumIndOrg5() {
        return pNumIndOrg5;
    }

    public void setpNumIndOrg5(BigDecimal pNumIndOrg5) {
        this.pNumIndOrg5 = pNumIndOrg5;
    }

    public BigDecimal getpDenIndOrg5() {
        return pDenIndOrg5;
    }

    public void setpDenIndOrg5(BigDecimal pDenIndOrg5) {
        this.pDenIndOrg5 = pDenIndOrg5;
    }

    public BigDecimal getpIndOrg5() {
        return pIndOrg5;
    }

    public void setpIndOrg5(BigDecimal pIndOrg5) {
        this.pIndOrg5 = pIndOrg5;
    }

    public BigDecimal getpBaseIndOrg5() {
        return pBaseIndOrg5;
    }

    public void setpBaseIndOrg5(BigDecimal pBaseIndOrg5) {
        this.pBaseIndOrg5 = pBaseIndOrg5;
    }

    public BigDecimal getpEsperadoIndOrg5() {
        return pEsperadoIndOrg5;
    }

    public void setpEsperadoIndOrg5(BigDecimal pEsperadoIndOrg5) {
        this.pEsperadoIndOrg5 = pEsperadoIndOrg5;
    }

    
}
