-- ������ �ӽ� ����
insert into customerInfo values('1111', '������');
insert into customerInfo values('2222', '������');
insert into customerInfo values('3333', '������');
insert into customerInfo values('4444', '����ȯ');

-- �������� �ӽ� ����
insert into accountInfo values('1111-1111', '1111', '0');
insert into accountInfo values('1111-1112', '1111', '1000');
insert into accountInfo values('1111-1113', '1111', '10000');
insert into accountInfo values('2222-2222', '2222', '123456');
insert into accountInfo values('3333-3333', '3333', '1000000');

-- �ŷ����� �ӽ� ����
insert into transaction values('1', '1111-1112', sysdate, 'I', '100', '������');
insert into transaction values('2', '1111-1112', sysdate, 'O', '200', '������');
insert into transaction values('3', '1111-1113', sysdate, 'I', '5000', '������');
insert into transaction values('4', '2222-2222', sysdate, 'I', '10000', '������');
insert into transaction values('5', '2222-2222', sysdate, 'O', '2000', '������');

-- �α� �ӽ� ����
insert into logdata values('20210912100000000PCHANNEL010001001', '/TEST1', '20210909100000', sysdate, '20210912100000000PCHANNEL010001', 'S', NULL, '{REQEUST:TEST1}', NULL);
insert into logdata values('20210912100000000PCHANNEL010001002', '/TEST1', '20210909100001', sysdate, '20210912100000000PCHANNEL010001', 'S', NULL, '{RESPONSE:TEST2}','100');
