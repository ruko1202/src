package uspn.test.appmanager.data;

public enum VioDocTypes {

    UPP(-1, 14, "���"),

    // ���������
    ZVUK(38, 2001, "���� - ��������� � ������ �� (��)"),
    ZNPF(78, 2002, "���� - ��������� � �������� �� ��� � ���"),
    ZNPFD(79, 2004, "����� - ��������� � ��������� �������� �� ��� � ���"),
    ZVIP(80, 2005, "���� - ��������� � �������� �� ��� � ���"),
    ZVIPD(81, 2007, "����� - ��������� � ��������� �������� �� ��� � ���"),
    DNPF(82, 2009, "���� - ��������� � �������� �� ��� � ���"),
    DNPFD(83, 2011, "����� - ��������� � ��������� �������� �� ��� � ���"),
    ZONP(84, 2012, "���� - ��������� �� ������ �� ������ 6%"),
    ZOONP(85, 2013, "����� - ��������� �� ������ ��������� �� ������"),
    UZVS(97, 2015, "���� - ����������� � ������"),
    UNPF(48, 2014, "���� - ����������� ���"),

    // �������� ���������
    V_SPN_DOG_NVS(107, 320, "���� - ������ ������� (1-� ��� �������� ����������)"),
    V_SPN_ZAJAV(93, 93, "���� - ������ �� ���������� � ��� (2-� ��� �������� �����������)"),
    V_SPN_R_SUD(109, 322, "����-� - ������ ������� ���� (4-� ��� �������� ����������)"),
    V_MSK_OTKAZ(110, 1010, "����-� - ������ ��� ��������� (5-� ��� �������� ����������)"),
    V_SPN_ANLIS(108, 321, "����-� - ������ ��� ������������� �������� ���"),
    V_UVED_COURT(134, 63, "���� - ������� ���� (7-� ��� �������� ����������)"),
    V_UVED_OTKAZ_MSK(126, 1026, "���� - ����������� ��� �����"),
    V_UVED_OZV_SPN(68, 68, "������� - ��������� �� �������� ���"),
    V_UVED_O_NAZNACHENII(69, 69, "������� - ������������ ������� ���"),
    V_UVED_VIPLATI(114, 2021, "��� - �������� �������"),
    V_UVED_PP(125, 2030, "����-� - �������� ������� ��"),
    V_ZAPROS(112, 2019, "���� - ������ � ��� �� (15-� ��� �������� ����������)"),
    V_UVED_INV_EST_P(130, 2034, "�����-� - ���������� �������������� ��� ���������� (39-� ��� �������� ����������)"),
    V_UVED_INV_NOT_EST_P(131, 2035, "����� - ���������� �������������� ��� (40-� ��� �������� ����������)"),
    V_UVED_SPN_NEVSTUP_NPF(138, 2039, "�����-��� - �������� ��� ����� ��� ������� (28-��� �������� ����������)"),
    V_SVEDENIYA_SVERKA_ILS(113, 2020, "���� - ��� ������ (29-� ��� �������� ����������)"),
    V_UVED_DEATH(143, 2042, "���-��� - �������� � ������ �� ��� (85-� ��� �������� ����������)");

    final Integer id;
    final Integer ehdId;
    final String label;

    VioDocTypes(Integer id, Integer ehdId, String label) {
        this.id = id;
        this.ehdId = ehdId;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public Integer getEhdId() {
        return ehdId;
    }

    public static VioDocTypes valueOf(Integer id) {
        for (VioDocTypes type : values()) {
            if (type.getId().equals(id)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return label;
    }
}