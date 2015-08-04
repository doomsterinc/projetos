/*Query que lista contratos para envio por e-mail*/
SELECT
account_analytic_account.x_token_contrato,
account_analytic_account.code,
res_partner.name,
res_partner.email
FROM
account_analytic_account,
res_partner

WHERE
account_analytic_account.x_cobrar_por_boleto = true
AND
res_partner.id = account_analytic_account.partner_id
/*Query que lista contratos para envio por e-mail*/
