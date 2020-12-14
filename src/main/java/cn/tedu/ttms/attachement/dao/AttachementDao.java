package cn.tedu.ttms.attachement.dao;

import cn.tedu.ttms.attachement.entity.Attchement;

public interface AttachementDao {
	int insertObject(Attchement entity);
	/**根据摘要信息获取记录数*/
	int getRowCountByDigest(String fileDisgest);
}
